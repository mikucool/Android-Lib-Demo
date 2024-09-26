package com.hzz.materialdmoe.ui.components.buttons.common

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hzz.materialdmoe.data.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonButtonsPlaygroundScreen(
    modifier: Modifier = Modifier,
    navController: NavController = NavController(LocalContext.current)
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val vm = viewModel<CommonButtonsPlaygroundViewModel>()
    val uiState = vm.uiState.collectAsState()
    LaunchedEffect(uiState.value.interactionState) {

        when (uiState.value.interactionState) {
            ButtonInteractionState.Pressed -> {
                interactionSource.emit(PressInteraction.Press(Offset.Zero))
            }

            ButtonInteractionState.Focused -> {
                interactionSource.emit(FocusInteraction.Focus())
            }

            ButtonInteractionState.Hovered -> {
                interactionSource.emit(HoverInteraction.Enter())
            }

            else -> {
                interactionSource.emit(PressInteraction.Release(PressInteraction.Press(Offset.Zero)))
            }
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Common Buttons")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentPadding = paddingValues
        ) {
            item {
                // Elevated Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(value = uiState.value.selectedButtonType == ButtonType.Common.Elevated) {
                            vm.updateUiState(selectedButtonType = ButtonType.Common.Elevated)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.value.selectedButtonType == ButtonType.Common.Elevated,
                        onCheckedChange = null
                    )
                    ElevatedButton(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(200.dp),
                        onClick = {},
                        enabled = if (uiState.value.selectedButtonType != ButtonType.Common.Elevated) true else uiState.value.isEnable,
                        interactionSource = if (uiState.value.selectedButtonType == ButtonType.Common.Elevated) interactionSource else remember { MutableInteractionSource() }
                    ) {
                        Text(text = "Elevated")
                    }
                }
                // Filled Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(value = uiState.value.selectedButtonType == ButtonType.Common.Filled) {
                            vm.updateUiState(selectedButtonType = ButtonType.Common.Filled)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.value.selectedButtonType == ButtonType.Common.Filled,
                        onCheckedChange = null
                    )
                    Button(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(200.dp),
                        enabled = if (uiState.value.selectedButtonType != ButtonType.Common.Filled) true else uiState.value.isEnable,
                        onClick = {},
                        interactionSource = if (uiState.value.selectedButtonType == ButtonType.Common.Filled) interactionSource else remember { MutableInteractionSource() }
                    ) {
                        Text(text = "Filled")
                    }
                }
                // Outlined Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(value = uiState.value.selectedButtonType == ButtonType.Common.Outlined) {
                            vm.updateUiState(selectedButtonType = ButtonType.Common.Outlined)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.value.selectedButtonType == ButtonType.Common.Outlined,
                        onCheckedChange = null
                    )
                    OutlinedButton(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(200.dp),
                        enabled = if (uiState.value.selectedButtonType != ButtonType.Common.Outlined) true else uiState.value.isEnable,
                        onClick = {},
                        interactionSource = if (uiState.value.selectedButtonType == ButtonType.Common.Outlined) interactionSource else remember { MutableInteractionSource() }
                    ) {
                        Text(text = "Outlined")
                    }
                }
                // FilledTonal Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(value = uiState.value.selectedButtonType == ButtonType.Common.FilledTonal) {
                            vm.updateUiState(selectedButtonType = ButtonType.Common.FilledTonal)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.value.selectedButtonType == ButtonType.Common.FilledTonal,
                        onCheckedChange = null
                    )
                    FilledTonalButton(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(200.dp),
                        enabled = if (uiState.value.selectedButtonType != ButtonType.Common.FilledTonal) true else uiState.value.isEnable,
                        onClick = {},
                        interactionSource = if (uiState.value.selectedButtonType == ButtonType.Common.FilledTonal) interactionSource else remember { MutableInteractionSource() }
                    ) {
                        Text(text = "Tonal")
                    }
                }
                // Text Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(value = uiState.value.selectedButtonType == ButtonType.Common.Text) {
                            vm.updateUiState(selectedButtonType = ButtonType.Common.Text)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.value.selectedButtonType == ButtonType.Common.Text,
                        onCheckedChange = null
                    )
                    TextButton(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(200.dp),
                        onClick = {},
                        enabled = if (uiState.value.selectedButtonType != ButtonType.Common.Text) true else uiState.value.isEnable,
                        interactionSource = if (uiState.value.selectedButtonType == ButtonType.Common.Text) interactionSource else remember { MutableInteractionSource() }
                    ) {
                        Text(text = "Text")
                    }
                }

                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Button Configuration",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 24.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.value.interactionState == ButtonInteractionState.Hovered,
                            onCheckedChange = { vm.updateUiState(interactionState = ButtonInteractionState.Hovered) }
                        )
                        Text(text = "Hovered")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.value.interactionState == ButtonInteractionState.Focused,
                            onCheckedChange = { vm.updateUiState(interactionState = ButtonInteractionState.Focused) }
                        )
                        Text(text = "Focused")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.value.interactionState == ButtonInteractionState.Pressed,
                            onCheckedChange = { vm.updateUiState(interactionState = ButtonInteractionState.Pressed) }
                        )
                        Text(text = "Pressed")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.value.interactionState == ButtonInteractionState.None,
                            onCheckedChange = { vm.updateUiState(interactionState = ButtonInteractionState.None) }
                        )
                        Text(text = "None")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Enable")
                        Switch(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            checked = uiState.value.isEnable,
                            onCheckedChange = { vm.updateUiState(isEnable = uiState.value.isEnable.not()) })
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonButtonsPlaygroundScreenPreview() {
    CommonButtonsPlaygroundScreen()
}