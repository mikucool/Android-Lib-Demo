package com.example.compose_shape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_shape.ui.BasicShapeScreen
import com.example.compose_shape.ui.RoundedShapeScreen
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

        composable<RoundedShape> {
            RoundedShapeScreen()
        }
    }
}

@Serializable
data class ShapeDemo(val destination: String = "shape_demo")

@Serializable
data class BasicShape(val description: String = "Basic Shape")

@Serializable
data class RoundedShape(val description: String = "Rounded Shape")