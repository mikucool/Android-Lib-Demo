package com.hzz.libdemo.ui.camera_demos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hzz.camera_demos.ui.basic.BasicCameraDemoScreen

@Composable
fun Demo1Screen(modifier: Modifier = Modifier) {
    BasicCameraDemoScreen(modifier = modifier.fillMaxSize())
}