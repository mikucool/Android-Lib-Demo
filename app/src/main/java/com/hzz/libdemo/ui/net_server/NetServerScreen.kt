package com.hzz.libdemo.ui.net_server

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetServerScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val viewModel = viewModel<NetServerViewModel>()
    val netServerUiState = viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = false) {
        viewModel.initNetInfo(context = context)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Net Server")
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back to previous screen"
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (netServerUiState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center)
                        .zIndex(1f),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Network Information:"
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        shape = CardDefaults.elevatedShape
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "Is in active network: ${netServerUiState.value.netInfo.isInActiveNetwork}")
                            Text(text = "Is has network capability: ${netServerUiState.value.netInfo.isHasNetworkCapacity}")
                            Text(text = "Is has internet capability: ${netServerUiState.value.netInfo.isHasInternetCapability}")
                            Text(text = "IP: ${netServerUiState.value.netInfo.ip}")
                            Text(text = "Public IP: ${netServerUiState.value.netInfo.publicIp}")
                            Text(text = "Network Type: ${netServerUiState.value.netInfo.networkType}")
                            Text(text = "Gateway: ${netServerUiState.value.netInfo.gateway}")
                            Text(text = "DNS: ${netServerUiState.value.netInfo.dns}")
                            Text(text = "MAC: ${netServerUiState.value.netInfo.macAddress}")
                            Text(text = "Subnet Mask: ${netServerUiState.value.netInfo.subnetMask}")
                        }

                    }
                    HorizontalDivider(modifier = Modifier.padding(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {

                            }) {
                            Text(text = "WLAN Server")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {

                            }) {
                            Text(text = "WAN Server")
                        }
                    }

                }
            }
        }
    }
}