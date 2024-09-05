package com.hzz.materialdmoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MaterialHomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "App bars")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Badges")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Buttons")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Cards")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Carousels")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Checkbox")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Chips")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Date pickers")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Dialogs")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Divider")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Lists")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Menus")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Navigation")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Progress indicators")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Radio buttons")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Search")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Sliders")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Snackbars")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Switches")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Tabs")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Text fields")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Time pickers")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Tooltips")
        }
    }
}