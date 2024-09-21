package com.hzz.materialdmoe.ui.components.app_bars.bottom

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBarScreen(modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val vm = viewModel<BottomAppBarViewModel>()
    val uiState by vm.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx() }
    val defaultWindowInsets = BottomAppBarDefaults.windowInsets
    val scrollBehavior =
        if (uiState.useScrollBehaviorAndConnect) BottomAppBarDefaults.exitAlwaysScrollBehavior() else null

    Scaffold(
        modifier = if (scrollBehavior != null) modifier.nestedScroll(scrollBehavior.nestedScrollConnection) else modifier,
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Action one was clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Action two was clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Action three was clicked", Toast.LENGTH_SHORT)
                            .show()
                    }) {
                        Icon(
                            Icons.Filled.Call,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Action four was clicked", Toast.LENGTH_SHORT)
                            .show()
                    }) {
                        Icon(
                            Icons.Filled.DateRange,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            Toast.makeText(context, "FAB was clicked", Toast.LENGTH_SHORT).show()
                        },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                },
                windowInsets = if (uiState.isUseCustomWindowInsets) object : WindowInsets {
                    override fun getBottom(density: Density): Int {
                        return uiState.windowInsetsRect.bottom.toInt()
                    }

                    override fun getLeft(density: Density, layoutDirection: LayoutDirection): Int {
                        return uiState.windowInsetsRect.left.toInt()
                    }

                    override fun getRight(density: Density, layoutDirection: LayoutDirection): Int {
                        return uiState.windowInsetsRect.right.toInt()
                    }

                    override fun getTop(density: Density): Int {
                        return uiState.windowInsetsRect.top.toInt()
                    }

                } else defaultWindowInsets,
                scrollBehavior = if (uiState.isUseCustomWindowInsets) scrollBehavior else null
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp)
                .nestedScroll(scrollBehavior?.nestedScrollConnection ?: object :
                    NestedScrollConnection {}),
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.useScrollBehaviorAndConnect,
                            onValueChange = { vm.switchUsingScrollBehaviorAndConnectState(!uiState.useScrollBehaviorAndConnect) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.useScrollBehaviorAndConnect,
                        onCheckedChange = null
                    )
                    Text("Using Scroll Behavior and Connect")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isUseCustomWindowInsets,
                            onValueChange = { vm.switchUsingCustomWindowInsetsState(!uiState.isUseCustomWindowInsets) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isUseCustomWindowInsets,
                        onCheckedChange = null
                    )
                    Text("Using Custom Window Insets")
                }

                if (uiState.isUseCustomWindowInsets) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)

                    ) {
                        Text(text = "Window Inset Left", modifier = Modifier.width(200.dp))
                        Slider(
                            value = uiState.windowInsetsRect.left,
                            onValueChange = {
                                vm.updateWindowInsets(
                                    uiState.windowInsetsRect.copy(
                                        left = it
                                    )
                                )
                            },
                            valueRange = 0f..screenWidth,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)

                    ) {
                        Text(text = "Window Inset Top", modifier = Modifier.width(200.dp))
                        Slider(
                            value = uiState.windowInsetsRect.top,
                            onValueChange = {
                                vm.updateWindowInsets(
                                    uiState.windowInsetsRect.copy(
                                        top = it
                                    )
                                )
                            },
                            valueRange = 0f..screenHeight,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)

                    ) {
                        Text(text = "Window Inset Right", modifier = Modifier.width(200.dp))
                        Slider(
                            value = uiState.windowInsetsRect.right,
                            onValueChange = {
                                vm.updateWindowInsets(
                                    uiState.windowInsetsRect.copy(
                                        right = it
                                    )
                                )
                            },
                            valueRange = 0f..screenWidth,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)

                    ) {
                        Text(text = "Window Inset Bottom", modifier = Modifier.width(200.dp))
                        Slider(
                            value = uiState.windowInsetsRect.bottom,
                            onValueChange = {
                                vm.updateWindowInsets(
                                    uiState.windowInsetsRect.copy(
                                        bottom = it
                                    )
                                )
                            },
                            valueRange = 0f..screenWidth,
                        )
                    }
                }
            }
            items(items = MaterialDemoLocalRepository.getFruitList(), key = { it }) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomAppBarScreenPreview() {
    BottomAppBarScreen()
}