package com.hzz.libdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hzz.libdemo.ui.nav.AppNavGraph
import com.hzz.libdemo.ui.theme.LibDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LibDemoTheme {
                AppNavGraph(modifier = Modifier.fillMaxSize(), navController = navController)
            }
        }
    }
}



