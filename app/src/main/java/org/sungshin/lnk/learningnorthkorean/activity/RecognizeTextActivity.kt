package org.sungshin.lnk.learningnorthkorean.activity

import android.graphics.*
import android.hardware.Camera
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.android.synthetic.main.activity_recognize_text.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.util.TessOCR
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth




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

    fun viewToBitmap(view: SurfaceView): Bitmap {
        val bitmap = Bitmap.createBitmap(surface.width, surface.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        view.setZOrderOnTop(true)
        view.draw(canvas)
        view.setZOrderOnTop(false)

        view.draw(canvas)

        val path = Environment.getExternalStorageDirectory().toString()
        var fOut: OutputStream? = null
        val counter = 0
        val file = File(path, "Test$counter.jpg") // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut) // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        fOut!!.flush() // Not really required
        fOut!!.close() // do not forget to close the stream

        MediaStore.Images.Media.insertImage(contentResolver, file.absolutePath, file.name, file.name)
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
            val tessOCR = TessOCR(filesDir, assets, viewToBitmap(surface))
            tessOCR.initTess()
            val result = tessOCR.processImage()

            alert {
                title = "인식 결과"
                message = if (result.isEmpty()) "인식된 결과가 없습니다. 다시 시도하시겠습니까?" else result
                yesButton {
                    message = "확인"
                }
                noButton() {
                    message = "취소"
                }
            }.show()
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