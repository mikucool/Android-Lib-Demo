package com.hzz.live_wallpaper.controller

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.hzz.live_wallpaper.data.Star
import kotlin.math.sin

class DefaultLiveWallpaperController(private val context: Context){
    private var surfaceHolder: SurfaceHolder? = null

    // screen size
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    // stars
    private val stars = mutableListOf<Star>()

    // handler
    private val mainHandler = Handler(Looper.getMainLooper())

    // runnable
    private val drawRunnable = Runnable { drawWallpaper() }
    fun onCreate() {
    }

    fun onSurfaceCreated(surfaceHolder: SurfaceHolder?) {
        this.surfaceHolder = surfaceHolder
        screenWidth = surfaceHolder?.surfaceFrame?.height() ?: 0
        screenHeight = surfaceHolder?.surfaceFrame?.width() ?: 0
        initData()
    }

    fun onVisibilityChanged(visible: Boolean) {
        if (visible) {
            mainHandler.post(drawRunnable)
        }
    }

    private fun drawWallpaper() {
        var canvas: Canvas? = null
        try {
            canvas = surfaceHolder?.lockCanvas()
            canvas?.let {
                it.drawColor(Color.BLACK) // Background color
                val paint = Paint()
                for (star in stars) {
                    paint.alpha = star.alpha
                    paint.color = Color.WHITE
                    it.drawCircle(star.x, star.y, star.size, paint)
                    // Update alpha for twinkling effect
                    star.alpha =
                        (sin(System.currentTimeMillis() / 100.0 + star.x) * 128 + 128).toInt()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            canvas?.let { surfaceHolder?.unlockCanvasAndPost(canvas) }
        }

    }

    fun onTouchEvent(event: MotionEvent?) {
        // Handle touch events if needed
    }

    private fun initData() {
        // Initialize stars
        for (i in 0 until 100) { // Number of stars
            stars.add(
                Star(
                    x = (Math.random() * screenWidth).toFloat(),
                    y = (Math.random() * screenHeight).toFloat(),
                    size = (Math.random() * 5 + 1).toFloat(),
                    alpha = 255
                )
            )
        }
    }

    fun onDestroy() {
//        mainHandler.removeCallbacksAndMessages(drawRunnable)
    }

}