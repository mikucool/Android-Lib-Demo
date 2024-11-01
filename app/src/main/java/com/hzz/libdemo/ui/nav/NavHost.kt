package com.hzz.libdemo.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hzz.libdemo.ui.camera_demos.Demo1Screen
import com.hzz.libdemo.ui.file_management.FileManagementScreen
import com.hzz.libdemo.ui.home.HomeScreen
import com.hzz.libdemo.ui.live_wallpaper.LiveWallpaperScreen
import com.hzz.libdemo.ui.material_demos.MaterialDemoScreen
import com.hzz.libdemo.ui.net_server.NetServerScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home("home")
    ) {
        composable<Home> {
            HomeScreen(navController = navController)
        }
        composable<LiveWallpaper> {
            LiveWallpaperScreen(navController = navController)
        }
        composable<NetServer> {
            NetServerScreen(navController = navController)
        }
        composable<CameraDemo1> {
            Demo1Screen()
        }
        composable<MaterialDemo> {
            MaterialDemoScreen()
        }
        composable<FileManagement> {
            FileManagementScreen()
        }
    }
}

