package com.hzz.legacyandroidviews.playground.component

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.addListener
import com.hzz.legacyandroidviews.R
import com.hzz.legacyandroidviews.playground.dp
import kotlin.math.cos
import kotlin.math.sin

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val dialContainer = FrameLayout(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
    }

    // 背景图层：始终保持在 dialContainer 的第 0 层
    private val bgView = ImageView(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        scaleType = ImageView.ScaleType.FIT_CENTER
        setImageResource(R.drawable.pic_bg_lucky_pass_content)
    }

    init {
        // 将背景图添加到转盘容器中最底层
        dialContainer.addView(bgView, 0)
        addView(dialContainer)
    }

    /**
     * 设置转盘背景图片资源（会自动随转盘旋转）
     */
    fun setDialBackground(resId: Int) {
        bgView.setImageResource(resId)
    }

    /**
     * 设置奖项视图
     * @param labels 奖项标签（如 ["100", "谢谢参与", "再来一次"]）
     * @param radiusPx 奖项距中心的半径（单位：px）
     */
    fun setupDialItems(labels: List<String>, radiusPx: Float = 250f) {
        // 移除除背景以外的 item 视图
        for (i in dialContainer.childCount - 1 downTo 1) {
            dialContainer.removeViewAt(i)
        }

        val itemCount = labels.size
        val centerX = width / 2f
        val centerY = height / 2f

        labels.forEachIndexed { index, label ->
            val angleDeg = 360f / itemCount * index
            val angleRad = Math.toRadians(angleDeg.toDouble())

            val itemView = TextView(context).apply {
                text = label
                setBackgroundColor(Color.YELLOW)
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
                layoutParams = LayoutParams(120.dp.toInt(), 40.dp.toInt())
                x = (centerX + radiusPx * cos(angleRad) - 60.dp).toFloat()
                y = (centerY + radiusPx * sin(angleRad) - 20.dp).toFloat()
                rotation = angleDeg
                tag = "item"
            }

            dialContainer.addView(itemView)
        }
    }

    /**
     * 执行旋转动画
     * @param targetIndex 要落在哪个奖项索引（从0开始）
     * @param roundCount 转几圈（完整360度）
     * @param itemCount 奖项总数（应与 setupDialItems 的 labels.size 一致）
     * @param onEnd 动画结束后的回调
     */
    fun rotateDial(
        targetIndex: Int,
        roundCount: Int = 5,
        itemCount: Int,
        duration: Long = 4000,
        onEnd: (() -> Unit)? = null
    ) {
        val degreesPerItem = 360f / itemCount
        val finalAngle = 360f * roundCount + (360f - degreesPerItem * targetIndex)

        ObjectAnimator.ofFloat(dialContainer, "rotation", 0f, finalAngle).apply {
            this.duration = duration
            interpolator = DecelerateInterpolator()
            start()
            onEnd?.let {
                addListener(onEnd = { it() })
            }
        }
    }
}