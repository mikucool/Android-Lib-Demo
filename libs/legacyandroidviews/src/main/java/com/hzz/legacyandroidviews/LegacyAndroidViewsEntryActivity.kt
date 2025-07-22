package com.hzz.legacyandroidviews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hzz.legacyandroidviews.playground.ClipBoardDemoActivity
import com.hzz.legacyandroidviews.playground.DrawerDemoActivity
import com.hzz.legacyandroidviews.playground.FragmentStarterActivity.Companion.startFragment
import com.hzz.legacyandroidviews.playground.GalleryDemoActivity
import com.hzz.legacyandroidviews.playground.GradientTextFragment
import com.hzz.legacyandroidviews.playground.PlinkoGameDemoActivity
import com.hzz.legacyandroidviews.playground.PositionActivity
import com.hzz.legacyandroidviews.playground.ScaleLayoutPlaygroundActivity
import com.hzz.legacyandroidviews.playground.WatermarkRemovalDemoActivity
import com.hzz.legacyandroidviews.playground.WebViewDemoActivity

class LegacyAndroidViewsEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_legacy_android_views_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            startActivity(Intent(this, ScaleLayoutPlaygroundActivity::class.java))
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, ClipBoardDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, WatermarkRemovalDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            startActivity(Intent(this, WebViewDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            startActivity(Intent(this, GalleryDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            startActivity(Intent(this, DrawerDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            startActivity(Intent(this, PlinkoGameDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            startActivity(Intent(this, PositionActivity::class.java))
        }

        findViewById<Button>(R.id.button9).setOnClickListener {
            startFragment(GradientTextFragment::class.java)
        }


    }
}