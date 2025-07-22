package com.hzz.legacyandroidviews.playground

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hzz.legacyandroidviews.R

class GradientTextFragment: Fragment(R.layout.fragment_gradient_text) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onCreate() called with: this.view = ${this.view}")
        this.view?.findViewById<TextView>(R.id.myText)?.post {
            val text = "多彩文字"
            val spannable = SpannableString(text)

            val rainbowColors = intArrayOf(
                Color.RED,           // 红
                0xFFFF7F00.toInt(),  // 橙
                Color.YELLOW,        // 黄
                Color.GREEN,         // 绿
                Color.CYAN,          // 青
                Color.BLUE,          // 蓝
                0xFF8B00FF.toInt()   // 紫（靛+紫融合）
            )
            val rainbowPositions = floatArrayOf(
                0.0f,     // RED
                1f / 6f,  // ORANGE
                2f / 6f,  // YELLOW
                3f / 6f,  // GREEN
                4f / 6f,  // CYAN
                5f / 6f,  // BLUE
                1.0f      // PURPLE
            )

            // 应用 Span
            spannable.setSpan(
                VerticalGradientSpan(rainbowColors, rainbowPositions),
                0, text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            this.requireView().findViewById<TextView>(R.id.myText).text = spannable
        }
    }
    class VerticalGradientSpan(
        private val colors: IntArray,
        private val positions: FloatArray
    ) : CharacterStyle(), UpdateAppearance {

        override fun updateDrawState(tp: TextPaint) {
            val height = tp.textSize
            val shader = LinearGradient(
                0f, 0f, 0f, height,
                colors,
                positions,
                Shader.TileMode.CLAMP
            )
            tp.shader = shader
        }
    }
}