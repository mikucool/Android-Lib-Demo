package com.hzz.materialdmoe.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hzz.materialdmoe.ui.components.app_bars.AppBarsScreen
import com.hzz.materialdmoe.ui.components.app_bars.bottom.BottomAppBarScreen
import com.hzz.materialdmoe.ui.components.app_bars.top.CenterAlignedTopAppBarScreen
import com.hzz.materialdmoe.ui.components.app_bars.top.LargeTopAppBarScreen
import com.hzz.materialdmoe.ui.components.app_bars.top.MediumTopAppBarScreen
import com.hzz.materialdmoe.ui.components.app_bars.top.SmallTopAppBarScreen
import com.hzz.materialdmoe.ui.components.badge.BadgeScreen
import com.hzz.materialdmoe.ui.components.buttons.ButtonsScreen
import com.hzz.materialdmoe.ui.components.buttons.common.CommonButtonsPlaygroundScreen
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
        /**App Bars Start*/
        composable<MaterialComponent.MaterialAppBars> {
            AppBarsScreen(navController = navController)
        }
        composable<TopAppBar.Small> {
            SmallTopAppBarScreen(navController = navController)
        }
        composable<TopAppBar.CenterAligned> {
            CenterAlignedTopAppBarScreen(navController = navController)
        }
        composable<TopAppBar.Medium> {
            MediumTopAppBarScreen(navController = navController)
        }
        composable<TopAppBar.Large> {
            LargeTopAppBarScreen(navController = navController)
        }
        composable<BottomAppBar> {
            BottomAppBarScreen()
        }
        /**App Bars End*/

        /**Badge Start*/
        composable<MaterialComponent.MaterialBadges> {
            BadgeScreen()
        }
        /**Badge End*/

        /**Buttons Start*/
        composable<MaterialComponent.MaterialButtons> {
            ButtonsScreen(navController = navController)
        }
        composable<CommonButtonSPlayground> {
            CommonButtonsPlaygroundScreen(navController = navController)
        }

        /**Buttons End*/

    }
}
