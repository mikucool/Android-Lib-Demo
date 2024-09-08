package com.hzz.materialdmoe.data

import com.hzz.materialdmoe.ui.nav.MaterialComponent

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
}