package com.hzz.materialdmoe.ui.components.app_bars

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context = LocalContext.current
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "App Bars",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Top App Bars",
                modifier = Modifier.align(Alignment.Start),
                style = MaterialTheme.typography.bodyLarge
            )
            Card(modifier = Modifier.padding(vertical = 12.dp)) {
                Text(
                    text = "Appearance:\n" +
                            "Top app bars display navigation, actions, and text at the top of a screen\n" +
                            "Purpose:\n" +
                            "Provides access to key tasks and information. Generally hosts a title, core action items, and certain navigation items.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(
                    items = MaterialDemoLocalRepository.getTopAppBarComponents(),
                    key = { it.description }
                ) {
                    AppInfoCard(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(256.dp)
                            .padding(end = 12.dp),
                        appBarInfo = it.description,
                        onClick = {
                            try {
                                navController.navigate(it)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AppInfoCard(modifier: Modifier = Modifier, appBarInfo: String = "", onClick: () -> Unit = {}) {
    ElevatedCard(modifier = modifier, onClick = onClick) {
        Text(
            text = appBarInfo,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}