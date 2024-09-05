package com.hzz.materialdmoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hzz.materialdmoe.ui.MaterialHomeScreen
import com.hzz.materialdmoe.ui.theme.LibDemoTheme

class MaterialUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibDemoTheme {
                MaterialHomeScreen()
            }
        }
    }
}
