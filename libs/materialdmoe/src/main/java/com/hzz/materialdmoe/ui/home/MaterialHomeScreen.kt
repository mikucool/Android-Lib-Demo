package com.hzz.materialdmoe.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository
import com.hzz.materialdmoe.ui.nav.MaterialComponent

@Composable
fun MaterialHomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val components: List<MaterialComponent> = MaterialDemoLocalRepository.getMaterialComponents()
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(components, key = { it.description }) { component ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {
                    try {
                        navController.navigate(component)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            context,
                            "Not implemented yet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(
                    text = component.description,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp),
                )
            }
        }
    }
}