package com.hzz.materialdmoe.data

import androidx.compose.ui.graphics.Color
import com.hzz.materialdmoe.ui.nav.MaterialComponent
import com.hzz.materialdmoe.ui.nav.TopAppBar

object MaterialDemoLocalRepository {
    fun getMaterialComponents() = listOf(
        MaterialComponent.MaterialAppBars(),
        MaterialComponent.MaterialBadges(),
        MaterialComponent.MaterialButtons(),
        MaterialComponent.MaterialCards(),
        MaterialComponent.MaterialCarousels(),
        MaterialComponent.MaterialCheckbox(),
        MaterialComponent.MaterialChips(),
        MaterialComponent.MaterialDatePicker(),
        MaterialComponent.MaterialDialogs(),
        MaterialComponent.MaterialDivider(),
        MaterialComponent.MaterialLists(),
        MaterialComponent.MaterialMenus(),
        MaterialComponent.MaterialNavigation(),
        MaterialComponent.MaterialProgressIndicators(),
        MaterialComponent.MaterialRadioButtons(),
        MaterialComponent.MaterialSearch(),
        MaterialComponent.MaterialSliders(),
        MaterialComponent.MaterialSnackbars(),
        MaterialComponent.MaterialSwitches(),
        MaterialComponent.MaterialTabs(),
        MaterialComponent.MaterialTextFields(),
        MaterialComponent.MaterialTimePicker(),
        MaterialComponent.MaterialTooltips(),
    )

    fun getTopAppBarComponents() = listOf(
        TopAppBar.Small(),
        TopAppBar.CenterAligned(),
        TopAppBar.Medium(),
        TopAppBar.Large(),
    )

    fun getFruitList() = listOf(
        "Apple",
        "Banana",
        "Cherry",
        "Date",
        "Fig",
        "Grape",
        "Kiwi",
        "Lemon",
        "Mango",
        "Nectarine",
        "Orange",
        "Peach",
        "Quince",
        "Raisin",
        "Strawberry",
        "Watermelon",
        "Blueberry",
        "Cherry",
    )

    fun getRandomColorList(size: Int): List<Color> {
        val result = mutableListOf<Color>()
        for (i in 0 until size) {
            val value = Color(
                (0..255).random(),
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
            result.add(value)
        }
        return result
    }
}