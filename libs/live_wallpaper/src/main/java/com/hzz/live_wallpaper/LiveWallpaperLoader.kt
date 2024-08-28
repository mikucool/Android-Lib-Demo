package com.hzz.live_wallpaper

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.hzz.live_wallpaper.service.LiveWallpaperService

object LiveWallpaperLoader {
    fun load(context: Context, launcher: ActivityResultLauncher<Intent>) {
        // launcher live wallpaper service
        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
            WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
            ComponentName(context, LiveWallpaperService::class.java)
        )
        launcher.launch(intent)
    }




}
