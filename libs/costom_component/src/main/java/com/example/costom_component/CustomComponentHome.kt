package com.example.costom_component

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
fun CustomComponentHome(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate(LuckyPass)
        }) {
            Text(text = "Lucky Pass")
        }
        Button(onClick = {
            navController.navigate(CircleCarousel)
        }) {
            Text(text = "Circle Carousel")
        }
        Button(onClick = {
            navController.navigate(SlotMachine)
        }) {
            Text(text = "Slot Machine")
        }
    }
}