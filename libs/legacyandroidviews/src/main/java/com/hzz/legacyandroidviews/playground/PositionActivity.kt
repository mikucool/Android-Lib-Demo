package com.hzz.legacyandroidviews.playground

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import com.hzz.legacyandroidviews.R

class PositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_position)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<View>(R.id.outerLayout).doOnLayout {
            updateInnerLayoutPosition()
        }


    }


    private fun updateInnerLayoutPosition() {
        val innerLayout = findViewById<View>(R.id.innerLayout)
        val innerLayoutPosition = IntArray(2)
        innerLayout.getLocationOnScreen(innerLayoutPosition)
        Log.d("PositionActivity", "innerLayoutPosition: ${innerLayoutPosition[0]}, ${innerLayoutPosition[1]}")
        val screenHeight = resources.displayMetrics.heightPixels
        val gap = screenHeight - innerLayoutPosition[1]
        Log.d("PositionActivity", "gap: $gap")
        val layoutParams = innerLayout.layoutParams as? androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
        layoutParams?.let {
            it.topMargin = gap * 3
            innerLayout.layoutParams = it
        }

    }

}