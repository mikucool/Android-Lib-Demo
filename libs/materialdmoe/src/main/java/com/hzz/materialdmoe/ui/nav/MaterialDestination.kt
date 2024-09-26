package com.hzz.materialdmoe.ui.nav

import kotlinx.serialization.Serializable

@Serializable
object MaterialHome

@Serializable
sealed class MaterialComponent {
    abstract val description: String

    @Serializable
    data class MaterialAppBars(override val description: String = "App bars") : MaterialComponent()

    @Serializable
    data class MaterialBadges(override val description: String = "Badges") : MaterialComponent()

    @Serializable
    data class MaterialButtons(override val description: String = "Buttons") : MaterialComponent()

    @Serializable
    data class MaterialCards(override val description: String = "Cards") : MaterialComponent()

    @Serializable
    data class MaterialCarousels(override val description: String = "Carousels") :
        MaterialComponent()

    @Serializable
    data class MaterialCheckbox(override val description: String = "Checkbox") : MaterialComponent()

    @Serializable
    data class MaterialChips(override val description: String = "Chips") : MaterialComponent()

    @Serializable
    data class MaterialDatePicker(override val description: String = "Date pickers") :
        MaterialComponent()

    @Serializable
    data class MaterialDialogs(override val description: String = "Dialogs") : MaterialComponent()

    @Serializable
    data class MaterialDivider(override val description: String = "Divider") : MaterialComponent()

    @Serializable
    data class MaterialLists(override val description: String = "Lists") : MaterialComponent()

    @Serializable
    data class MaterialMenus(override val description: String = "Menus") : MaterialComponent()

    @Serializable
    data class MaterialNavigation(override val description: String = "Navigation") :
        MaterialComponent()

    @Serializable
    data class MaterialProgressIndicators(override val description: String = "Progress indicators") :
        MaterialComponent()

    @Serializable
    data class MaterialRadioButtons(override val description: String = "Radio buttons") :
        MaterialComponent()

    @Serializable
    data class MaterialSearch(override val description: String = "Search") : MaterialComponent()

    @Serializable
    data class MaterialSliders(override val description: String = "Sliders") : MaterialComponent()

    @Serializable
    data class MaterialSnackbars(override val description: String = "Snackbars") :
        MaterialComponent()

    @Serializable
    data class MaterialSwitches(override val description: String = "Switches") : MaterialComponent()

    @Serializable
    data class MaterialTabs(override val description: String = "Tabs") : MaterialComponent()

    @Serializable
    data class MaterialTextFields(override val description: String = "Text fields") :
        MaterialComponent()

    @Serializable
    data class MaterialTimePicker(override val description: String = "Time pickers") :
        MaterialComponent()

    @Serializable
    data class MaterialTooltips(override val description: String = "Tooltips") : MaterialComponent()
}

@Serializable
sealed class TopAppBar {
    abstract val description: String

    @Serializable
    data class Small(override val description: String = "Small Top App Bar") : TopAppBar()

    @Serializable
    data class CenterAligned(override val description: String = "Center Aligned Top App Bar") :
        TopAppBar()

    @Serializable
    data class Medium(override val description: String = "Medium Top App Bar") : TopAppBar()

    @Serializable
    data class Large(override val description: String = "Large Top App Bar") : TopAppBar()
}

@Serializable
data class BottomAppBar(val description: String = "Bottom App Bar")

@Serializable
data class CommonButtonSPlayground(val description: String = "Common Button Playground")

