package com.hzz.legacyandroidviews.playground

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.hzz.legacyandroidviews.R
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.DrawerPopupView

class DrawerDemoActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawer_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.openDrawerByPopupButton).setOnClickListener {
            XPopup.Builder(this)
                .asCustom(DrawerPopup(this))
                .show()
        }
        drawerLayout = findViewById(R.id.main)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        findViewById<Button>(R.id.openDrawerByDrawerLayoutButton).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)

        }
    }

    class DrawerPopup(context: Context) : DrawerPopupView(context) {
        override fun getImplLayoutId(): Int {
            return R.layout.layout_drawer
        }

        override fun onCreate() {
            super.onCreate()
        }
    }

}