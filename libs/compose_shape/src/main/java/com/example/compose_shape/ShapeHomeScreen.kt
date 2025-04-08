package com.example.compose_shape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ShapeHomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate(BasicShape())
        }) {
            Text(text = "Basic Shape")
        }
        Button(onClick = {
            navController.navigate(RoundedShape())
        }) {
            Text(text = "Rounded Shape")
        }
    }
}