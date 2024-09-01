package com.hzz.local_server.data.entity

data class ServerNetworkInfo(
    var isInActiveNetwork: Boolean = false,
    var isHasNetworkCapacity: Boolean = false,
    var isHasInternetCapability: Boolean = false,
    var ip: String = "",
    var publicIp: String = "",
    var networkType: String = "",
    var gateway: String = "",
    var dns: List<String> = emptyList(),
    var macAddress: String = "",
    var subnetMask: String = "",
)
