package com.hzz.libdemo.ui.shape

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.compose_shape.ShapeDemoActivity

@Composable
fun ShapeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, ShapeDemoActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("Open Shape Demo Screen")
        }
    }
}