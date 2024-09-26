package com.hzz.materialdmoe.ui.components.buttons.common

import androidx.lifecycle.ViewModel
import com.hzz.materialdmoe.data.ButtonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommonButtonsPlaygroundViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CommonButtonsUiState())
    val uiState: StateFlow<CommonButtonsUiState> = _uiState.asStateFlow()

    fun updateUiState(
        selectedButtonType: ButtonType.Common? = null,
        interactionState: ButtonInteractionState? = null,
        isEnable: Boolean? = null
    ) {
        isEnable?.let { _uiState.value = _uiState.value.copy(isEnable = it) }
        selectedButtonType?.let { _uiState.value = _uiState.value.copy(selectedButtonType = it) }
        interactionState?.let { _uiState.value = _uiState.value.copy(interactionState = it) }
    }
}

data class CommonButtonsUiState(
    val isEnable: Boolean = true,
    val selectedButtonType: ButtonType.Common = ButtonType.Common.Filled,
    val interactionState: ButtonInteractionState = ButtonInteractionState.None
)

enum class ButtonInteractionState {
    None, Hovered, Focused, Pressed
}

