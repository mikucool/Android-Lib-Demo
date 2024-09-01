package com.hzz.libdemo.ui.nav

import kotlinx.serialization.Serializable

@Serializable
data class Home(val route: String)
@Serializable
data class LiveWallpaper(val route: String)
@Serializable
data class NetServer(val route: String)
@Serializable
data class CameraDemo1(val route: String)