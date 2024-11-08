package com.hzz.calendar

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hzz.calendar.data.CalendarEventInfo

@Composable
fun CalendarDemosEntryScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<CalendarDemoViewModel>()
    val context = LocalContext.current
    val requiredPermissions = arrayOf(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { maps ->
        val allGranted = maps.values.all { it }
        if (allGranted) {
            Toast.makeText(context, "All Permissions Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Not All Permissions Granted", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // Request permission
                permissionLauncher.launch(requiredPermissions)
            }

        }) {
            Text(text = "Request Calendar Permission")
        }
        Button(onClick = {
            viewModel.addEventToCalendar(
                context,
                CalendarEventInfo()
            )
        }) {
            Text(text = "Add Event To Calendar")
        }
    }
}