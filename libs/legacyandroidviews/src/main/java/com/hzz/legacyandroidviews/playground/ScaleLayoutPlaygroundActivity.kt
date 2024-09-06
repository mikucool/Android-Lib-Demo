package com.hzz.legacyandroidviews.playground

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Size
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hzz.legacyandroidviews.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ScaleLayoutPlaygroundActivity : AppCompatActivity() {
    private lateinit var contentSize: Size
    private lateinit var topImageRectF: RectF
    private lateinit var bottomImageRectF: RectF
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scale_layout_playground_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<ConstraintLayout>(R.id.content).apply {
            post {
                contentSize = Size(width, height)
            }
        }
        findViewById<ImageView>(R.id.topImage).apply {
            post {
                topImageRectF = RectF(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat()
                )
            }
        }
        findViewById<ImageView>(R.id.bottomImage).apply {
            post {
                bottomImageRectF = RectF(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat()
                )
            }
        }
        findViewById<Button>(R.id.saveToGalleryButton).setOnClickListener {
            scaleContentShapeToBitmap(
                contentSize = contentSize,
                topImageRectF = topImageRectF,
                bottomImageRectF = bottomImageRectF,
                callback = ::saveBitmapToGallery
            )
        }
        findViewById<Button>(R.id.saveToFileButton).setOnClickListener {
            scaleContentShapeToBitmap(
                contentSize = contentSize,
                topImageRectF = topImageRectF,
                bottomImageRectF = bottomImageRectF,
                callback = ::saveBitmapToFile
            )
        }
    }

    /**
     * Scales the content shape to a bitmap.
     */
    private fun scaleContentShapeToBitmap(
        contentSize: Size,
        scale: Int = 2,
        topImageRectF: RectF,
        bottomImageRectF: RectF,
        callback: ((Bitmap) -> Unit)? = null
    ) {
        val contentWidth = contentSize.width
        val contentHeight = contentSize.height
        // creating a bitmap
        val bitmap = Bitmap.createBitmap(
            contentWidth * scale,
            contentHeight * scale,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.GREEN)
        // drawing the top image
        canvas.drawRect(
            topImageRectF.left * scale,
            topImageRectF.top * scale,
            topImageRectF.right * scale,
            topImageRectF.bottom * scale,
            Paint().apply {
                color = Color.RED
            }
        )
        // drawing the bottom image
        canvas.drawRect(
            bottomImageRectF.left * scale,
            bottomImageRectF.top * scale,
            bottomImageRectF.right * scale,
            bottomImageRectF.bottom * scale,
            Paint().apply {
                color = Color.BLUE
            }
        )
        // save bitmap to gallery
        callback?.invoke(bitmap)
    }

    private fun saveBitmapToFile(bitmap: Bitmap) {
        val picturesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(picturesDir, "test-${System.currentTimeMillis()}.png")
        try {
            val outputStream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun saveBitmapToGallery(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "test-${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = this.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        uri?.let {
            val outputStream: OutputStream? = this.contentResolver.openOutputStream(it)
            outputStream?.use { stream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            }
        }
    }

}