package com.hzz.camera_demos.ui.basic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BasicCameraDemoViewModel: ViewModel() {
    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun takePhoto(controller: CameraController, applicationContext: Context) {
        controller.takePicture(
            ActivityCompat.getMainExecutor(applicationContext),
            object : OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    image.use {
                        val matrix = Matrix().apply {
                            postRotate(it.imageInfo.rotationDegrees.toFloat())
                        }
                        val rotatedBitmap = Bitmap.createBitmap(
                            it.toBitmap(),
                            0,
                            0,
                            it.width,
                            it.height,
                            matrix,
                            true
                        )
                        Log.d("Camera", "Photo taken: $rotatedBitmap")
                        _bitmaps.value += rotatedBitmap
                        // TODO: save bitmap
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            }
        )
    }
    fun recordVideo(controller: CameraController, applicationContext: Context) {
        // TODO: record video
    }
}