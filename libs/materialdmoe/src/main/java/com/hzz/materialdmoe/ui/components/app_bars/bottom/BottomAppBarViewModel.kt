package com.hzz.materialdmoe.ui.components.app_bars.bottom

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomAppBarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BottomAppBarUiState())
    val uiState: StateFlow<BottomAppBarUiState> = _uiState.asStateFlow()

    fun switchUsingCustomWindowInsetsState(useCustomWindowInsets: Boolean) {
        _uiState.value = _uiState.value.copy(isUseCustomWindowInsets = useCustomWindowInsets)
    }

    fun updateWindowInsets(rect: Rect) {
        _uiState.value = _uiState.value.copy(windowInsetsRect = rect)
    }

    data class BottomAppBarUiState(
        val windowInsetsRect: Rect = Rect(0f, 0f, 0f, 0f),
        val isUseCustomWindowInsets: Boolean = false,
    )
}