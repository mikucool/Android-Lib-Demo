package com.hzz.libdemo.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hzz.libdemo.ui.home.HomeScreen
import com.hzz.libdemo.ui.live_wallpaper.LiveWallpaperScreen

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
    }
}
