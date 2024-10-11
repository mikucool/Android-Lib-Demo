package com.hzz.legacyandroidviews.playground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.exifinterface.media.ExifInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hzz.legacyandroidviews.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class WatermarkRemovalDemoActivity : AppCompatActivity() {
    private lateinit var targetBitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareTargetBitmap()
        enableEdgeToEdge()
        setContentView(R.layout.activity_watermark_removal_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.removeWatermarkButton).setOnClickListener {
            loadBitmapFromInternalStorage()
        }
        findViewById<ImageView>(R.id.originalImage).setImageBitmap(targetBitmap)
    }

    private fun prepareTargetBitmap() {
        val originalBitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic1)
        targetBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(targetBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        val watermarkInfo = generateWatermark()
        canvas.drawBitmap(watermarkInfo.watermark, 0f, 0f, null)
        val stringBuilder = StringBuilder()
        val restoreBitmap = Bitmap.createBitmap(
            originalBitmap,
            watermarkInfo.x, // The x-coordinate of the top-left corner of the crop rectangle
            watermarkInfo.y, // The y-coordinate of the top-left corner of the crop rectangle
            watermarkInfo.width,  // The width of the crop rectangle
            watermarkInfo.height // The height of the crop rectangle
        )
        stringBuilder.append("x:${watermarkInfo.x}\n")
        stringBuilder.append("y:${watermarkInfo.y}\n")
        stringBuilder.append("width:${watermarkInfo.width}\n")
        stringBuilder.append("height:${watermarkInfo.height}\n")
        stringBuilder.append("capacity:${watermarkInfo.capacity}\n")
        stringBuilder.append("restoreBitmapString:${restoreBitmap.toBase64()}\n")
        saveBitmapToFile(targetBitmap, stringBuilder.toString())
    }

    private fun generateWatermark(
        x: Int = 0,
        y: Int = 0,
        width: Int = 800,
        height: Int = 800
    ): WatermarkInfo {
        val watermarkBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(watermarkBitmap)
        canvas.drawColor(0x80000000.toInt())
        val capacity: Int = width * height * 4
        return WatermarkInfo(
            x,
            y,
            width,
            height,
            capacity,
            watermarkBitmap
        )
    }

    private fun saveBitmapToFile(bitmap: Bitmap, extraInfo: String? = null) {
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
            modifyImageMetadata(extraInfo)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun modifyImageMetadata(extraInfo: String? = null) {
        val directory = this.getDir("imageDir", MODE_PRIVATE)
        val file = File(directory, "watermark.png")
        val exif = ExifInterface(file.absolutePath)
        exif.setAttribute(ExifInterface.TAG_USER_COMMENT, extraInfo)
        exif.saveAttributes()
        Log.e("WatermarkRemovalDemoActivity", "$extraInfo")
    }

    private fun loadBitmapFromInternalStorage() {
        val directory = this.getDir("imageDir", MODE_PRIVATE)
        val file = File(directory, "watermark.png")
        val exif = ExifInterface(file.absolutePath)
        val data = exif.getAttribute(ExifInterface.TAG_USER_COMMENT)
        Log.e("WatermarkRemovalDemoActivity", "loadBitmapFromInternalStorage: $data")
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val restoreBitmap = data?.substringAfter("restoreBitmapString:")?.dropLast(1)
        val resultBitmap = targetBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        if (restoreBitmap != null) {
            canvas.drawBitmap(restoreBitmap.toBitmap(), 0f, 0f, null)
        }
        findViewById<ImageView>(R.id.resultImage).setImageBitmap(resultBitmap)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun Bitmap.toBase64(): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encode(byteArray)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun String.toBitmap(): Bitmap {
        val byteArray = Base64.decode(this)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    data class WatermarkInfo(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int,
        val capacity: Int,
        val watermark: Bitmap,
    )

}