package com.example.compose_shape.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

@Composable
fun BasicShapeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 6,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()
                    onDrawBehind {
                        drawPath(roundedPolygonPath, color = Color.Blue)
                    }
                }
                .fillMaxSize()
        )
    }
}