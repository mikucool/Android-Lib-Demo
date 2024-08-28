package com.hzz.live_wallpaper.service

import android.content.Context
import android.service.wallpaper.WallpaperService
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.hzz.live_wallpaper.controller.DefaultLiveWallpaperController

class LiveWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return LiveWallpaperEngine(this)
    }

    inner class LiveWallpaperEngine(context: Context) : WallpaperService.Engine() {
        private var controller: DefaultLiveWallpaperController? =
            DefaultLiveWallpaperController(context = context)

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
            controller?.onCreate()
        }

        override fun onSurfaceCreated(holder: SurfaceHolder?) {
            super.onSurfaceCreated(holder)
            // surface holder was already initialized
            controller?.onSurfaceCreated(surfaceHolder = holder)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            controller?.onVisibilityChanged(visible = visible)
        }

        override fun onTouchEvent(event: MotionEvent?) {
            super.onTouchEvent(event)
            controller?.onTouchEvent(event = event)
        }

        override fun onDestroy() {
            super.onDestroy()
            controller?.onDestroy()
        }
    }
}