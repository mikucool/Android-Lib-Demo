package com.example.costom_component.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs

@SuppressLint("UnrememberedMutableState")
@Composable
fun SlotMachineScreenFinal() {
    val items = listOf("A", "B", "C", "D", "E")
    var isSpinning by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val baseItemHeightDp = 50.dp
    val maxItemHeightDp = baseItemHeightDp * 1.5f // 中间元素最大高度
    val minItemHeightDp = baseItemHeightDp * 0.5f // 边缘元素最小高度
    val maxItemHeightPx = with(LocalDensity.current) { maxItemHeightDp.toPx() }

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
                    .height(maxItemHeightDp * 4) // 调整容器高度以适应缩放效果
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    state = listState,
                    reverseLayout = true,
                    modifier = Modifier.height(maxItemHeightDp * 4),
//                    userScrollEnabled = false
                ) {
                    items(Integer.MAX_VALUE) { index ->
                        val item = items[index % items.size]

                        // 计算当前项在可见区域中的位置
                        val itemInfo by derivedStateOf {
                            listState.layoutInfo.visibleItemsInfo.find { it.index == index }
                        }

                        val offsetAndIsCenter: Pair<Float, Boolean> = itemInfo?.let {
                            val center =
                                listState.layoutInfo.viewportStartOffset + listState.layoutInfo.viewportSize.height / 2
                            // 需要根据是否reverse判断
                            val isCenterItem =
                                it.offset.toFloat() in (center - (maxItemHeightPx / 2))..(center + (maxItemHeightPx / 2))
                            val offset = (it.offset + it.size / 2 - center).toFloat()
                            Pair(offset, isCenterItem)
                        } ?: Pair(0f, false)

                        // 根据距离中心的距离计算缩放比例 (0-1)
                        val distanceFromCenter by derivedStateOf {
                            abs(offsetAndIsCenter.first) / (listState.layoutInfo.viewportSize.height / 2f)
                        }

                        val scale = 1f - (distanceFromCenter * 0.5f).coerceIn(0f, 0.5f)

                        // 计算高度和透明度
                        val itemHeight =
                            (minItemHeightDp + (maxItemHeightDp - minItemHeightDp) * scale)
                        val alpha = 0.5f + scale * 0.5f
                        val backgroundColor =
                            if (offsetAndIsCenter.second == true) Color.Red else Color.Blue

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(itemHeight)
                                .padding(vertical = 8.dp)
                                .background(backgroundColor)
                                .graphicsLayer {
                                    scaleX = scale
                                    scaleY = scale
                                    this.alpha = alpha
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item,
                                style = TextStyle(
                                    fontSize = (32 * scale).sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
                HorizontalDivider(
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

                            while (withFrameNanos { it } - startTime < 3_000_000_000L) { // 3秒間
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

                            val finalTargetIndex = firstVisibleItem.index + offsetToTarget

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