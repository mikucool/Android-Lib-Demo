package com.hzz.materialdmoe.ui.components.app_bars.top

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context = LocalContext.current
) {
    val vm = viewModel<SmallTopBarViewModel>()
    val uiState by vm.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    if (uiState.isConfigTitle) {
                        Text("Small Top App Bar")
                    }
                },
                navigationIcon = {
                    if (uiState.isConfigNavigationIcon) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (uiState.isConfigActions) {
                        IconButton(onClick = {
                            Toast.makeText(context, "Menu Clicked", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                scrollBehavior = if (uiState.isConfigScrollBehavior) scrollBehavior else null
            )
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(
                    if (uiState.isConfigScrollBehavior) scrollBehavior.nestedScrollConnection
                    else object : NestedScrollConnection {}
                ),
            contentPadding = paddingValue
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isConfigTitle,
                            onValueChange = { vm.updateConfigTitle(!uiState.isConfigTitle) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isConfigTitle,
                        onCheckedChange = null
                    )
                    Text("Config Title")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isConfigNavigationIcon,
                            onValueChange = { vm.updateConfigNavigationIcon(!uiState.isConfigNavigationIcon) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isConfigNavigationIcon,
                        onCheckedChange = null
                    )
                    Text("Config Navigation Icon")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isConfigActions,
                            onValueChange = { vm.updateConfigActions(!uiState.isConfigActions) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isConfigActions,
                        onCheckedChange = null
                    )
                    Text("Config Actions")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isConfigScrollBehavior,
                            onValueChange = { vm.updateConfigScrollBehavior(!uiState.isConfigScrollBehavior) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isConfigScrollBehavior,
                        onCheckedChange = null
                    )
                    Text("Config Scroll Behavior")
                }
            }

            items(items = vm.getFruits(), key = { it }) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SmallTopAppBarScreenPreview() {
    SmallTopAppBarScreen(navController = NavController(LocalContext.current))
}


