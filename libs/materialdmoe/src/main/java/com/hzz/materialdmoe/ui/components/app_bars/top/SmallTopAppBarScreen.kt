package com.hzz.materialdmoe.ui.components.app_bars.top

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hzz.materialdmoe.ui.components.app_bars.TopAppScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context = LocalContext.current
) {
    val vm = viewModel<SmallTopBarViewModel>()
    val uiState by vm.uiState.collectAsState()
    val scrollBehavior = when (uiState.appBarScrollBehavior) {
        TopAppScrollBehavior.None -> null
        TopAppScrollBehavior.Pinned -> TopAppBarDefaults.pinnedScrollBehavior()
        TopAppScrollBehavior.ExitUntilCollapsed -> TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        TopAppScrollBehavior.EnterAlwaysCollapsed -> TopAppBarDefaults.enterAlwaysScrollBehavior()
    }
    val configuration = LocalConfiguration.current
    val screenWidth = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.appBarTitle, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    if (uiState.isShowNavigationIcon) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (uiState.isShowBarEndActions) {
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
                scrollBehavior = scrollBehavior,
                windowInsets = object : WindowInsets {
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

                }
            )
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior?.nestedScrollConnection ?: object :
                    NestedScrollConnection {}),
            contentPadding = paddingValue
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(12.dp)
                        .height(56.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxSize(),
                        value = uiState.appBarTitle,
                        onValueChange = { vm.updateAppBarTitle(it) },
                        placeholder = { Text("Edit title") }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isShowNavigationIcon,
                            onValueChange = { vm.switchShowingNavigationIconState(!uiState.isShowNavigationIcon) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isShowNavigationIcon,
                        onCheckedChange = null
                    )
                    Text("Show Navigation Icon")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.isShowBarEndActions,
                            onValueChange = { vm.switchShowingAppBarEndActionState(!uiState.isShowBarEndActions) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Checkbox(
                        checked = uiState.isShowBarEndActions,
                        onCheckedChange = null
                    )
                    Text("Show App Bar End Actions")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.appBarScrollBehavior == TopAppScrollBehavior.None,
                            onValueChange = { vm.updateScrollBehavior(TopAppScrollBehavior.None) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    RadioButton(
                        selected = uiState.appBarScrollBehavior == TopAppScrollBehavior.None,
                        onClick = {}
                    )
                    Text("No Behavior")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.appBarScrollBehavior == TopAppScrollBehavior.EnterAlwaysCollapsed,
                            onValueChange = { vm.updateScrollBehavior(TopAppScrollBehavior.EnterAlwaysCollapsed) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    RadioButton(
                        selected = uiState.appBarScrollBehavior == TopAppScrollBehavior.EnterAlwaysCollapsed,
                        onClick = {}
                    )
                    Text("Enter Always Collapsed")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.appBarScrollBehavior == TopAppScrollBehavior.ExitUntilCollapsed,
                            onValueChange = { vm.updateScrollBehavior(TopAppScrollBehavior.ExitUntilCollapsed) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    RadioButton(
                        selected = uiState.appBarScrollBehavior == TopAppScrollBehavior.ExitUntilCollapsed,
                        onClick = {}
                    )
                    Text("Exit Until Collapsed")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .toggleable(
                            value = uiState.appBarScrollBehavior == TopAppScrollBehavior.Pinned,
                            onValueChange = { vm.updateScrollBehavior(TopAppScrollBehavior.Pinned) }
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    RadioButton(
                        selected = uiState.appBarScrollBehavior == TopAppScrollBehavior.Pinned,
                        onClick = {}
                    )
                    Text("Pinned")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)

                ) {
                    Text(text = "Window Inset Left", modifier = Modifier.width(148.dp))
                    Slider(
                        value = uiState.windowInsetsRect.left,
                        onValueChange = { vm.updateWindowInsets(uiState.windowInsetsRect.copy(left = it)) },
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
                    Text(text = "Window Inset Top", modifier = Modifier.width(148.dp))
                    Slider(
                        value = uiState.windowInsetsRect.top,
                        onValueChange = { vm.updateWindowInsets(uiState.windowInsetsRect.copy(top = it)) },
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
                    Text(text = "Window Inset Right", modifier = Modifier.width(148.dp))
                    Slider(
                        value = uiState.windowInsetsRect.right,
                        onValueChange = { vm.updateWindowInsets(uiState.windowInsetsRect.copy(right = it)) },
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
                    Text(text = "Window Inset Bottom", modifier = Modifier.width(148.dp))
                    Slider(
                        value = uiState.windowInsetsRect.bottom,
                        onValueChange = { vm.updateWindowInsets(uiState.windowInsetsRect.copy(bottom = it)) },
                        valueRange = 0f..screenWidth,
                    )
                }

            }

            items(items = vm.getFruits(), key = { it }) { item ->
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

@Composable
@Preview
fun SmallTopAppBarScreenPreview() {
    SmallTopAppBarScreen(navController = NavController(LocalContext.current))
}


