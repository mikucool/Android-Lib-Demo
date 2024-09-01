package com.hzz.local_server.server.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.DhcpInfo
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission
import com.hzz.local_server.data.entity.ServerNetworkInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.URL
import java.util.Locale

class NetUtil {
    @RequiresPermission(allOf = ["android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE"])
    suspend fun getNetworkInfo(context: Context): ServerNetworkInfo {
        val serverNetworkInfo = getLocalNetworkInfo(context)
        val publicIpAddress = getPublicIpAddress()
        if (publicIpAddress != null) {
            serverNetworkInfo.publicIp = publicIpAddress
        }
        return serverNetworkInfo
    }

    @RequiresPermission("android.permission.INTERNET")
    private suspend fun getPublicIpAddress(): String? {
        return withContext(Dispatchers.IO) {
            try {
                val publicIp = URL("https://api.ipify.org").readText()
                publicIp
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    @RequiresPermission(allOf = ["android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE"])
    fun getLocalNetworkInfo(context: Context): ServerNetworkInfo {
        val serverNetworkInfo = ServerNetworkInfo()
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return serverNetworkInfo
        serverNetworkInfo.isInActiveNetwork = true
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return serverNetworkInfo
        serverNetworkInfo.isHasNetworkCapacity = true
        when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                serverNetworkInfo.networkType = "WIFI"
                serverNetworkInfo.ip = getWifiIpAddress(wifiManager = wifiManager)
                serverNetworkInfo.subnetMask = getSubnetMask(wifiManager = wifiManager)
                serverNetworkInfo.gateway = getGateway(wifiManager = wifiManager)
                serverNetworkInfo.dns = getDnsServers()
                serverNetworkInfo.macAddress = getMacAddress("wlan0")
            }

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                serverNetworkInfo.networkType = getMobileNetworkType(context)
                serverNetworkInfo.macAddress = getMacAddress("rmnet0")
                serverNetworkInfo.dns = getDnsServers()
            }

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                serverNetworkInfo.networkType = "ETHERNET"
            }

            else -> {
                serverNetworkInfo.networkType = "UNKNOWN"
            }
        }

        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            serverNetworkInfo.isHasInternetCapability = true
        }
        return serverNetworkInfo
    }

    // Get the IP Address
    private fun getWifiIpAddress(wifiManager: WifiManager): String {
        val ipAddress = wifiManager.connectionInfo.ipAddress
        return String.format(
            locale = Locale.getDefault(),
            "%d.%d.%d.%d",
            ipAddress and 0xff,
            ipAddress shr 8 and 0xff,
            ipAddress shr 16 and 0xff,
            ipAddress shr 24 and 0xff
        )
    }

    // Get the Subnet Mask
    private fun getSubnetMask(wifiManager: WifiManager): String {
        val dhcpInfo: DhcpInfo = wifiManager.dhcpInfo
        val netmask = dhcpInfo.netmask
        return String.format(
            locale = Locale.getDefault(),
            "%d.%d.%d.%d",
            netmask and 0xff,
            netmask shr 8 and 0xff,
            netmask shr 16 and 0xff,
            netmask shr 24 and 0xff
        )
    }

    // Get the Gateway
    private fun getGateway(wifiManager: WifiManager): String {
        val dhcpInfo: DhcpInfo = wifiManager.dhcpInfo
        val gateway = dhcpInfo.gateway
        return String.format(
            locale = Locale.getDefault(),
            "%d.%d.%d.%d",
            gateway and 0xff,
            gateway shr 8 and 0xff,
            gateway shr 16 and 0xff,
            gateway shr 24 and 0xff
        )
    }


    // Get DNS Servers
    private fun getDnsServers(): List<String> {
        val dnsServers = mutableListOf<String>()
        try {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface = networkInterfaces.nextElement()
                val inetAddresses = networkInterface.inetAddresses
                while (inetAddresses.hasMoreElements()) {
                    val inetAddress = inetAddresses.nextElement()
                    if (inetAddress is Inet4Address && !inetAddress.isLoopbackAddress) {
                        dnsServers.add(inetAddress.hostAddress ?: "")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dnsServers
    }

    // Get the MAC Address
    private fun getMacAddress(interfaceName: String = "wlan0"): String {
        try {
            val networkInterface = NetworkInterface.getByName(interfaceName)
            if (networkInterface != null) {
                val macBytes = networkInterface.hardwareAddress
                if (macBytes != null) {
                    return macBytes.joinToString(separator = ":") { byte -> "%02X".format(byte) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    private fun getMobileNetworkType(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return when (telephonyManager.dataNetworkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE,
            TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT,
            -> "2G"

            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0,
            TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA,
            TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA,
            TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD,
            TelephonyManager.NETWORK_TYPE_HSPAP -> "3G"

            TelephonyManager.NETWORK_TYPE_LTE -> "4G"

            TelephonyManager.NETWORK_TYPE_NR -> "5G"

            TelephonyManager.NETWORK_TYPE_IWLAN -> "IWLAN"

            else -> "Unknown"
        }
    }

}