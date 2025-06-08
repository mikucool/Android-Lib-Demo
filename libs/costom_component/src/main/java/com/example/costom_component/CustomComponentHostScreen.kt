package com.example.costom_component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.costom_component.components.CircleCarouselScreen
import com.example.costom_component.components.LuckyPassScreen
import com.example.costom_component.components.SlotMachineScreenFinal

@Composable
fun CustomComponentHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = CustomComponentHome,
        modifier = modifier
    ) {

        composable<CustomComponentHome> {
            CustomComponentHome(navController = navController)
        }
        composable<LuckyPass> {
            LuckyPassScreen()
        }
        composable<CircleCarousel> {
            CircleCarouselScreen()
        }
        composable<SlotMachine> {
            SlotMachineScreenFinal()
        }


    }
}