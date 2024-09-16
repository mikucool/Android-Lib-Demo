package com.hzz.materialdmoe.ui.components.app_bars.top

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository
import com.hzz.materialdmoe.ui.components.app_bars.TopAppScrollBehavior
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SmallTopBarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun updateAppBarTitle(title: String) {
        _uiState.value = _uiState.value.copy(appBarTitle = title)
    }

    fun switchShowingNavigationIconState(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isShowNavigationIcon = isShow)
    }

    fun switchShowingAppBarEndActionState(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isShowBarEndActions = isShow)
    }

    fun updateScrollBehavior(behavior: TopAppScrollBehavior) {
        _uiState.value = _uiState.value.copy(appBarScrollBehavior = behavior)
    }

    fun updateWindowInsets(rect: Rect) {
        _uiState.value = _uiState.value.copy(windowInsetsRect = rect)
    }

    fun getFruits(): List<String> {
        return MaterialDemoLocalRepository.getFruitList()
    }

    data class UiState(
        val appBarTitle: String = "Small Top App Bar",
        val isShowNavigationIcon: Boolean = false,
        val isShowBarEndActions: Boolean = false,
        val appBarScrollBehavior: TopAppScrollBehavior = TopAppScrollBehavior.None,
        val windowInsetsRect: Rect = Rect(0f, 0f, 0f, 0f)
    )
}