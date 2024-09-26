package com.hzz.materialdmoe.data

import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

    fun getAllButtons(): List<ButtonInfo> {
        return listOf(
            ButtonInfo(
                id = 0,
                name = "Filled button",
                exampleActions = "Save / Confirm / Done",
                rational = "The filled button’s contrasting surface color makes it the most prominent button after the FAB. It’s used for final or unblocking actions in a flow.",
                emphasisLevel = "High",
                buttonType = ButtonType.Common.Filled,
                content = {
                    Button(onClick = {}) {
                        Text(text = "Filled")
                    }
                }
            ),
            ButtonInfo(
                id = 1,
                name = "Filled tonal button",
                exampleActions = "Save / Confirm / Done",
                rational = "Filled tonal buttons have a lighter background color and darker label color, making them less visually prominent than a regular, filled button. They’re still used for final or unblocking actions in a flow, but do so with less emphasis.",
                emphasisLevel = "Medium",
                buttonType = ButtonType.Common.FilledTonal,
                content = {
                    FilledTonalButton(onClick = {}) {
                        Text(text = "Filled tonal")
                    }
                }
            ),
            ButtonInfo(
                id = 2,
                name = "Elevated button",
                exampleActions = "Reply / View all / Add to cart / Take out of trash",
                rational = "Elevated buttons are essentially filled buttons with a lighter background color and a shadow. To prevent shadow creep, only use them when absolutely necessary, such as when the button requires visual separation from a patterned background.",
                emphasisLevel = "Medium",
                buttonType = ButtonType.Common.Elevated,
                content = {
                    ElevatedButton(onClick = {}) {
                        Text(text = "Elevated")
                    }
                }
            ),
            ButtonInfo(
                id = 3,
                name = "Outlined button",
                exampleActions = "Reply / View all / Add to cart / Take out of trash",
                rational = "Use an outlined button for actions that need attention but aren’t the primary action, such as See all or Add to cart. This is also the button to use for giving someone the opportunity to change their mind or escape a flow.",
                emphasisLevel = "Medium",
                buttonType = ButtonType.Common.Outlined,
                content = {
                    OutlinedButton(onClick = {}) {
                        Text(text = "Outlined")
                    }
                }
            ),
            ButtonInfo(
                id = 4,
                name = "Text button",
                exampleActions = "Learn more / View all / Change account / Turn on",
                rational = "Text buttons have less visual prominence, so should be used for low emphasis actions, such as an alternative option.",
                emphasisLevel = "Low",
                buttonType = ButtonType.Common.Text,
                content = {
                    TextButton(onClick = {}) {
                        Text(text = "Text")
                    }
                }
            ),
            ButtonInfo(
                id = 5,
                name = "Extended FAB",
                exampleActions = "Create Compose / New thread / New file",
                rational = "The extended FAB’s wider format and text label give it more visual prominence than a  FAB. It’s often used on larger screens where a FAB would seem too small.",
                emphasisLevel = "High"
            ),
            ButtonInfo(
                id = 6,
                name = "FAB",
                exampleActions = "Create Compose",
                rational = "The FAB remains the default component for a screen’s primary action. It comes in three sizes: small FAB, FAB, and large FAB.",
                emphasisLevel = "High"
            ),
            ButtonInfo(
                id = 7,
                name = "Segmented button",
                exampleActions = "Left align / Middle align / Right align",
                rational = "Segmented buttons have more visual prominence than a single icon button.",
                emphasisLevel = "Low"
            ),
            ButtonInfo(
                id = 8,
                name = "Icon button",
                exampleActions = "Add to Favorites / Print",
                rational = "The most compact and subtle type of button, icon buttons are used for optional supplementary actions such as “Bookmark” or “Star.”",
                emphasisLevel = "Low"
            )
        )
    }

}