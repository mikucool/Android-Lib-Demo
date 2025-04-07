package com.hzz.libdemo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hzz.libdemo.ui.CalendarDemo
import com.hzz.libdemo.ui.CameraDemo1
import com.hzz.libdemo.ui.FileManagement
import com.hzz.libdemo.ui.LiveWallpaper
import com.hzz.libdemo.ui.MaterialDemo
import com.hzz.libdemo.ui.NetServer
import com.hzz.libdemo.ui.ShapeDemo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            navController.navigate(LiveWallpaper("wallpaper_a"))
        }) {
            Text(text = "Live Wallpaper")
        }

        Button(onClick = {
            navController.navigate(NetServer("net_server"))
        }) {
            Text(text = "Net Server")
        }

        Button(onClick = {
            navController.navigate(CameraDemo1("camera_demo1"))
        }) {
            Text(text = "Camera Demo1")
        }
        Button(onClick = {
            navController.navigate(MaterialDemo("material_demo"))
        }) {
            Text(text = "Material Demo")
        }
        Button(onClick = {
            navController.navigate(FileManagement("file_management"))
        }) {
            Text(text = "File Management")
        }
        Button(onClick = {
            navController.navigate(CalendarDemo("calendar_demo"))
        }) {
            Text(text = "Calendar Demo")
        }
        Button(onClick = {
            navController.navigate(ShapeDemo("shape_demo"))
        }) {
            Text(text = "Shape Demo")
        }
    }
}
