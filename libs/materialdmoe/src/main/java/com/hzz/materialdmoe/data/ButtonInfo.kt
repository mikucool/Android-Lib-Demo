package com.hzz.materialdmoe.data

import androidx.compose.runtime.Composable

data class ButtonInfo(
    val id: Int,
    val name: String,
    val exampleActions: String,
    val rational: String,
    val emphasisLevel: String,
    val buttonType: ButtonType = ButtonType.Unknown,
    val content: @Composable () -> Unit = {}
) {

}
sealed class ButtonType {
    data object Unknown: ButtonType()
    abstract class Common : ButtonType() {
        data object Elevated : Common()
        data object Filled : Common()
        data object FilledTonal : Common()
        data object Outlined : Common()
        data object Text : Common()
        data object Unknown : Common()
    }
}
