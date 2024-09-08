package com.hzz.materialdmoe.ui.components.app_bars.top

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SmallTopBarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun updateConfigTitle(isConfig: Boolean) {
        _uiState.value = _uiState.value.copy(isConfigTitle = isConfig)
    }

    fun updateConfigNavigationIcon(isConfig: Boolean) {
        _uiState.value = _uiState.value.copy(isConfigNavigationIcon = isConfig)
    }

    fun updateConfigActions(isConfig: Boolean) {
        _uiState.value = _uiState.value.copy(isConfigActions = isConfig)
    }

    fun updateConfigScrollBehavior(isConfig: Boolean) {
        _uiState.value = _uiState.value.copy(isConfigScrollBehavior = isConfig)
    }

    fun getFruits(): List<String> {
        return MaterialDemoLocalRepository.getFruitList()
    }

    data class UiState(
        val isConfigTitle: Boolean = false,
        val isConfigNavigationIcon: Boolean = false,
        val isConfigActions: Boolean = false,
        val isConfigScrollBehavior: Boolean = false,
    )
}