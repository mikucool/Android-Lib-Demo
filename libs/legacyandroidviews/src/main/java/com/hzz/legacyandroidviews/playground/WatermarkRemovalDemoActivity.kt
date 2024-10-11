package com.hzz.legacyandroidviews.playground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.exifinterface.media.ExifInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hzz.legacyandroidviews.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class WatermarkRemovalDemoActivity : AppCompatActivity() {
    private lateinit var targetBitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareBitmap()
        enableEdgeToEdge()
        setContentView(R.layout.activity_watermark_removal_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.saveToInternal).setOnClickListener {
            saveBitmapToFile(targetBitmap)
            Toast.makeText(this, "Saved to internal storage successfully", Toast.LENGTH_SHORT)
                .show()
        }
        findViewById<Button>(R.id.removeWatermarkButton).setOnClickListener {
            loadBitmapFromInternalStorage()
        }
        findViewById<ImageView>(R.id.originalImage).setImageBitmap(targetBitmap)
    }

    private fun prepareBitmap() {
        val originalBitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic1)
        targetBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(targetBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        canvas.drawBitmap(generateWatermarkBitmap(), 0f, 0f, null)
    }

    private fun generateWatermarkBitmap(): Bitmap {
        val watermarkBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(watermarkBitmap)
        canvas.drawColor(0x80000000.toInt())
        return watermarkBitmap
    }

    private fun saveBitmapToFile(bitmap: Bitmap) {
        val directory = this.getDir("imageDir", MODE_PRIVATE)
        val file = File(directory, "watermark.png")
        try {
            FileOutputStream(file).use { out ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    out
                )
            }
            modifyImageMetadata()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun modifyImageMetadata() {
        val directory = this.getDir("imageDir", MODE_PRIVATE)
        val file = File(directory, "watermark.png")
        val exif = ExifInterface(file.absolutePath)
        val data = "0"
        exif.setAttribute(ExifInterface.TAG_USER_COMMENT, data)
        exif.saveAttributes()
        Log.e("WatermarkRemovalDemoActivity", "modifyImageMetadata: $data")
    }

    private fun loadBitmapFromInternalStorage(): Bitmap {
        val directory = this.getDir("imageDir", MODE_PRIVATE)
        val file = File(directory, "watermark.png")
        val exif = ExifInterface(file.absolutePath)
        val data = exif.getAttribute(ExifInterface.TAG_USER_COMMENT)
        Log.e("WatermarkRemovalDemoActivity", "loadBitmapFromInternalStorage: $data")
        return BitmapFactory.decodeFile(file.absolutePath)
    }

}