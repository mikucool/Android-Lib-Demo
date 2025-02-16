package com.hzz.legacyandroidviews.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.Random
import kotlin.math.abs


class PlinkoView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private var paint: Paint? = null
    private var ballPosition: PointF? = null
    private val ballRadius = 20f
    private val obstacleRadius = 30f
    private lateinit var obstacles: Array<PointF?>
    private var random: Random? = null

    init {
        init()
    }

    private fun init() {
        paint = Paint()
        paint!!.color = Color.BLUE
        paint!!.style = Paint.Style.FILL

        ballPosition = PointF()
        random = Random()

        // Initialize obstacles positions
        Log.d("PlinkoView", "onSizeChanged: width = $width, height = $height")
        obstacles = arrayOfNulls(9)
        for (i in 0..2) {
            for (j in 0..2) {
                obstacles[i * 3 + j] =
                    PointF(((j + 1) * width / 4).toFloat(), ((i + 1) * height / 4).toFloat())
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Reinitialize obstacles positions based on new size
        for (i in 0..2) {
            for (j in 0..2) {
                obstacles[i * 3 + j]!!.x = ((j + 1) * w / 4).toFloat()
                obstacles[i * 3 + j]!!.y = ((i + 1) * h / 4).toFloat()
            }
        }
        Log.d("PlinkoView", "onSizeChanged: width = $w, height = $h")
        ballPosition!!.x = (w / 2).toFloat()
        ballPosition!!.y = 0f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw obstacles
        paint!!.color = Color.RED
        for (obstacle in obstacles) {
            canvas.drawCircle(obstacle!!.x, obstacle.y, obstacleRadius, paint!!)
        }

        // Draw ball
        paint!!.color = Color.BLUE
        canvas.drawCircle(ballPosition!!.x, ballPosition!!.y, ballRadius, paint!!)

        // Update ball position
        updateBallPosition()
        invalidate() // Redraw the view
    }

    private fun updateBallPosition() {
        // Simple gravity effect
        ballPosition!!.y += 5f

        // Check for collision with obstacles
        for (obstacle in obstacles) {
            if (abs((ballPosition!!.x - obstacle!!.x).toDouble()) < ballRadius + obstacleRadius &&
                abs((ballPosition!!.y - obstacle.y).toDouble()) < ballRadius + obstacleRadius
            ) {
                // 50% chance to go left or right
                if (random!!.nextBoolean()) {
                    ballPosition!!.x -= 10f
                } else {
                    ballPosition!!.x += 10f
                }
            }
        }

        // Check if ball reaches the bottom
        if (ballPosition!!.y > height) {
            ballPosition!!.y = 0f
            ballPosition!!.x = (width / 2).toFloat()
        }
    }
}