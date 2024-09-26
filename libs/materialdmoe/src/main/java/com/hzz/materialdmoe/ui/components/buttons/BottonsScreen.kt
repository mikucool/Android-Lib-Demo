package com.hzz.materialdmoe.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hzz.materialdmoe.data.ButtonInfo
import com.hzz.materialdmoe.data.ButtonType
import com.hzz.materialdmoe.data.MaterialDemoLocalRepository
import com.hzz.materialdmoe.ui.nav.CommonButtonSPlayground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Material Buttons")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
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
            contentPadding = paddingValues,
        ) {
            item {

                Text(
                    text = "Emphasis Level:",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "-High emphasis – For the primary, most important, or most common action on a screen\n" +
                            "-Medium emphasis – For important actions that don’t distract from other onscreen elements\n" +
                            "-Low emphasis – For optional or supplementary actions with the least amount of prominence\n"
                )
                Text(
                    text = "Common Buttons",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Common buttons prompt most actions in a UI"
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items(
                        items = MaterialDemoLocalRepository.getAllButtons()
                            .filter { it.buttonType is ButtonType.Common },
                        key = { it.id }
                    ) {
                        ButtonInfoCard(
                            modifier = Modifier
                                .padding(end = 12.dp, top = 12.dp, bottom = 12.dp)
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            buttonInfo = it
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(onClick = { navController.navigate(CommonButtonSPlayground()) }) {
                        Text(text = "CommonButtonsPlayground")
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonInfoCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    buttonInfo: ButtonInfo
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        onClick = onClick
    ) {
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            buttonInfo.content()
        }
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            text = "emphasis level: ${buttonInfo.emphasisLevel}"
        )
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            text = "rational: \n${buttonInfo.rational}"
        )
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            text = "example actions: \n${buttonInfo.exampleActions}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsScreenPreview() {
    ButtonsScreen(navController = NavController(LocalContext.current))
}