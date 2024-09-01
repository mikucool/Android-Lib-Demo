package com.hzz.local_server.server

import android.content.Context
import java.net.ServerSocket

class WLANServer(private val port: Int, private val context: Context) {

    private lateinit var listenerSocket: ServerSocket

}