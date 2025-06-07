package com.example.costom_component.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.costom_component.R

@Composable
fun LuckyPassScreen(modifier: Modifier = Modifier) {
    val rotation = remember { Animatable(0f) }
    var isSpinning by remember { mutableStateOf(false) }

    LaunchedEffect(isSpinning) {
        if (isSpinning) {
            // 第一阶段：tween 加速动画
            rotation.animateTo(
                targetValue = rotation.value + 3600f, // 第一阶段的结束角度
                animationSpec = tween(
                    durationMillis = 2000, // 加速阶段持续时间
                    easing = FastOutLinearInEasing // 加速曲线
                )
            )

            rotation.animateTo(
                targetValue = rotation.value + 3600f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )

            isSpinning = false
        } else {
            println("rotation.value = ${rotation.value}")
            rotation.snapTo(rotation.value)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pic_bg_lucky_pass_content),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .rotate(rotation.value),
            contentScale = ContentScale.FillWidth
        )

        Button(
            onClick = { isSpinning = !isSpinning },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) { }
    }

}