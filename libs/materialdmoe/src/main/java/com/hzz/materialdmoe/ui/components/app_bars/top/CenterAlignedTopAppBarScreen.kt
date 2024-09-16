package com.hzz.materialdmoe.ui.components.app_bars.top

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context = LocalContext.current
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Menu Clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValue
        ) {

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = false,
                            onValueChange = {  }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = null
                    )
                    Text("Config Title")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = false,
                            onValueChange = {  }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = null
                    )
                    Text("Config Navigation Icon")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = false,
                            onValueChange = { }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = null
                    )
                    Text("Config Actions")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = false,
                            onValueChange = { }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = null
                    )
                    Text("Config Actions")
                }
            }
        }
    }
}

@Composable
@Preview
fun CenterAlignedTopAppBarScreenPreview() {
    CenterAlignedTopAppBarScreen(navController = NavController(LocalContext.current))

}