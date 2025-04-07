package com.hzz.libdemo.ui

import kotlinx.serialization.Serializable

@Serializable
data class Home(val route: String)

@Serializable
data class LiveWallpaper(val route: String)

@Serializable
data class NetServer(val route: String)

@Serializable
data class CameraDemo1(val route: String)

@Serializable
data class MaterialDemo(val route: String)

@Serializable
data class FileManagement(val route: String)

@Serializable
data class CalendarDemo(val route: String)

@Serializable
data class ShapeDemo(val route: String)