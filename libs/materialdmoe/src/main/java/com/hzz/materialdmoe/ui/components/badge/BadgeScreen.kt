package com.hzz.materialdmoe.ui.components.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BadgeScreen(modifier: Modifier = Modifier) {
    var badgeNumber by remember { mutableIntStateOf(0) }
    val badgeNumberText = if (badgeNumber > 9) "9+" else badgeNumber.toString()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BadgedBox(
            badge = {
                Badge {
                    Text(
                        badgeNumberText,
                        modifier = Modifier.semantics {
                            contentDescription = "$badgeNumber new notifications"
                        }
                    )
                }
            }
        ) {
            Icon(Icons.Filled.Star, contentDescription = "Favorite")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    if (badgeNumber > 0) {
                        badgeNumber--
                    }
                }
            ) {
                Text(text = "Decrease")
            }
            Button(
                onClick = { badgeNumber++ }
            ) {
                Text(text = "Increase")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeScreenPreview() {
    BadgeScreen()
}