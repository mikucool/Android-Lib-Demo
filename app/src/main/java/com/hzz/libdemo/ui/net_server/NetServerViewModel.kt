package com.hzz.libdemo.ui.net_server

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hzz.local_server.data.entity.ServerNetworkInfo
import com.hzz.local_server.server.utils.NetUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetServerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NetServerUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateNetworkInfo(networkInfo: ServerNetworkInfo) {
        _uiState.value = _uiState.value.copy(netInfo = networkInfo)
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    fun initNetInfo(context: Context) {
        updateLoading(true)
        viewModelScope.launch {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(context, "Please grant phone state permission", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } else {
                updateNetworkInfo(NetUtil().getNetworkInfo(context))
                updateLoading(false)
            }
        }
    }
}

data class NetServerUiState(
    val isLoading: Boolean = false,
    val netInfo: ServerNetworkInfo = ServerNetworkInfo()
)