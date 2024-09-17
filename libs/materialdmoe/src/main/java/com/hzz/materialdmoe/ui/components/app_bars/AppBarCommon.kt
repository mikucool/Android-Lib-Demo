package com.hzz.materialdmoe.ui.components.app_bars

import androidx.compose.ui.geometry.Rect


enum class TopAppScrollBehavior {
    None, EnterAlwaysCollapsed, ExitUntilCollapsed, Pinned
}


data class TopAppBarUiState(
    val appBarTitle: String = "TITLE SAMPLE",
    val isShowNavigationIcon: Boolean = false,
    val isShowBarEndActions: Boolean = false,
    val appBarScrollBehavior: TopAppScrollBehavior = TopAppScrollBehavior.None,
    val windowInsetsRect: Rect = Rect(0f, 0f, 0f, 0f),
    val isUseCustomWindowInsets: Boolean = false,
)