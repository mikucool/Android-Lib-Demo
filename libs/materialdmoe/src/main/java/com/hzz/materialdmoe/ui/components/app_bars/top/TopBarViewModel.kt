package com.hzz.materialdmoe.ui.components.app_bars.top

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository
import com.hzz.materialdmoe.ui.components.app_bars.TopAppBarUiState
import com.hzz.materialdmoe.ui.components.app_bars.TopAppScrollBehavior
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopBarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TopAppBarUiState())
    val uiState: StateFlow<TopAppBarUiState> = _uiState.asStateFlow()

    fun updateAppBarTitle(title: String) {
        _uiState.value = _uiState.value.copy(appBarTitle = title)
    }

    fun switchShowingNavigationIconState(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isShowNavigationIcon = isShow)
    }

    fun switchShowingAppBarEndActionState(isShow: Boolean) {
        _uiState.value = _uiState.value.copy(isShowBarEndActions = isShow)
    }
    fun switchUsingCustomWindowInsetsState(useCustomWindowInsets: Boolean) {
        _uiState.value = _uiState.value.copy(isUseCustomWindowInsets = useCustomWindowInsets)
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
}