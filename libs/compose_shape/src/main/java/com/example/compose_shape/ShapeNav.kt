package com.example.compose_shape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_shape.ui.BasicShapeScreen
import kotlinx.serialization.Serializable

@Composable
fun ShapeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = ShapeDemo(),
        modifier = modifier
    ) {

        composable<ShapeDemo> {
            ShapeHomeScreen(navController = navController)
        }

        composable<BasicShape> {
            BasicShapeScreen()
        }
    }
}

@Serializable
data class ShapeDemo(val destination: String = "shape_demo")

@Serializable
data class BasicShape(val description: String = "Basic Shape")