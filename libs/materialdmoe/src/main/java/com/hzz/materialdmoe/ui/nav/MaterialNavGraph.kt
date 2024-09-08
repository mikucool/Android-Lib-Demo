package com.hzz.materialdmoe.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hzz.materialdmoe.ui.components.AppBarsScreen
import com.hzz.materialdmoe.ui.home.MaterialHomeScreen

@Composable
fun MaterialNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = MaterialHome
    ) {
        composable<MaterialHome> {
            MaterialHomeScreen(navController = navController)
        }
        composable<MaterialComponent.MaterialAppBars> {
            AppBarsScreen()
        }

    }
}
