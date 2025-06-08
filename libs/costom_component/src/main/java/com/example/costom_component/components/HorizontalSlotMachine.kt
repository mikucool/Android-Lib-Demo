package com.example.costom_component.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun HorizontalSlotMachine() {
    val items = listOf("A", "B", "C", "D", "E")
    var isSpinning by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val itemWidthDp = 50.dp

    val centerOffset = 2

    LaunchedEffect(Unit) {
        val initialIndex = (Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % items.size)
        listState.scrollToItem(initialIndex - centerOffset)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(itemWidthDp * 5),
                contentAlignment = Alignment.Center
            ) {
                LazyRow(
                    state = listState,
                    modifier = Modifier.width(itemWidthDp * 5),
                    userScrollEnabled = false
                ) {
                    items(Integer.MAX_VALUE) { index ->
                        val item = items[index % items.size]
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(itemWidthDp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item,
                                style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center)
                            )
                        }
                    }
                }
                VerticalDivider(
                    modifier = Modifier.align(Alignment.Center),
                    thickness = 2.dp,
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (!isSpinning) {
                        isSpinning = true
                        coroutineScope.launch {
                            var currentVelocity = 0f
                            val acceleration = 2000f // 加速度 (pixels/sec^2)
                            val initialVelocity = 500f // 初速 (pixels/sec)

                            val startTime = withFrameNanos { it }
                            var lastFrameTime = startTime

                            while (withFrameNanos { it } - startTime < 3_000_000_000L) { // 2秒間
                                val currentFrameTime = withFrameNanos { it }
                                val deltaTime =
                                    (currentFrameTime - lastFrameTime) / 1_000_000_000f // 秒単位の経過時間
                                lastFrameTime = currentFrameTime

                                currentVelocity =
                                    (currentVelocity + acceleration * deltaTime).coerceAtLeast(
                                        initialVelocity
                                    )
                                val scrollAmount = currentVelocity * deltaTime

                                listState.scroll {
                                    scrollBy(scrollAmount)
                                }
                            }


                            val firstVisibleItem =
                                listState.layoutInfo.visibleItemsInfo.firstOrNull() ?: return@launch
                            val currentItemIndex = firstVisibleItem.index % items.size
                            val offsetToTarget =
                                (items.indexOf("A") - currentItemIndex + items.size) % items.size

                            val finalTargetIndex = firstVisibleItem.index + offsetToTarget + items.size * 100

                            listState.animateScrollToItem(index = finalTargetIndex - centerOffset)

                            isSpinning = false
                        }
                    }
                },
                enabled = !isSpinning
            ) {
                Text(text = if (isSpinning) "Spinning..." else "Spin to 'A'")
            }
        }
    }
}