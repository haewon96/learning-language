package org.sungshin.lnk.learningnorthkorean.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.view.SurfaceHolder
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.android.synthetic.main.activity_recognize_text.*
import org.jetbrains.anko.toast
import org.sungshin.lnk.learningnorthkorean.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import android.os.Environment.getExternalStorageDirectory
import java.util.*
import android.content.Intent
import android.net.Uri
import org.sungshin.lnk.learningnorthkorean.util.TessOCR


class RecognizeTextActivity : AppCompatActivity(), SurfaceHolder.Callback {
    lateinit var camera: Camera
    lateinit var surfaceHolder: SurfaceHolder

    var image: Bitmap? = null
    lateinit var mTess: TessBaseAPI //Tess API reference
    var datapath = "" //언어 데이터 경로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize_text)
        initView()
    }

    private fun takeScreenshot() {
        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)

        try {
            val mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg"

            val v1 = window.decorView.rootView
            v1.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v1.drawingCache)
            v1.isDrawingCacheEnabled = false

            val imageFile = File(mPath)

            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()

            openScreenshot(imageFile)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun openScreenshot(imageFile: File) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        val uri = Uri.fromFile(imageFile)
        intent.setDataAndType(uri, "image/*")
        startActivity(intent)
    }

    fun viewToBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(surface.width, surface.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        surface.setZOrderOnTop(true)
        surface.draw(canvas)
        surface.setZOrderOnTop(false)

        return bitmap
    }

    private fun initView() {
        actionBar?.hide()
        window.setFormat(PixelFormat.UNKNOWN)
        camera = Camera.open()
        camera.setDisplayOrientation(90)

        surfaceHolder = surface.holder
        surfaceHolder.addCallback(this)

        btn_capture.setOnClickListener {
            camera.stopPreview()
            val bitmap = Bitmap.createBitmap(surface.width, surface.height, Bitmap.Config.ARGB_8888)
            val tessOCR = TessOCR(filesDir, assets, bitmap)
            tessOCR.initTess()
            val result = tessOCR.processImage()
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        if (surfaceHolder.surface == null) return

        // 카메라 설정
        val parameters = camera.parameters
        val focusModes = parameters.supportedFocusModes
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE))
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
        else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO))
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_AUTO

        camera.parameters = parameters
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        camera.stopPreview()
        camera.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}