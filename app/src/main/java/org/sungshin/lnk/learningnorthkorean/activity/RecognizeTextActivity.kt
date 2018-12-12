package org.sungshin.lnk.learningnorthkorean.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.android.synthetic.main.activity_recognize_text.*
import org.jetbrains.anko.toast
import org.sungshin.lnk.learningnorthkorean.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class RecognizeTextActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private val resultPermission = 100
    lateinit var camera: Camera
    lateinit var surfaceHolder: SurfaceHolder

    var image: Bitmap? = null
    lateinit var mTess: TessBaseAPI //Tess API reference
    var datapath = "" //언어 데이터 경로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize_text)
        askForPermission()
        initView()
    }

    private fun checkFile(dir: File) {
        //디렉토리가 없으면 디렉토리를 만들고 그후에 파일을 카피
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles()
        }
        //디렉토리가 있지만 파일이 없으면 파일카피 진행
        if (dir.exists()) {
            val datafile = File(datapath + "/tessdata/kor.traineddata")
            if (!datafile.exists()) {
                copyFiles()
            }
        }
    }

    fun processImage() {
        val OCRresult: String = mTess.utF8Text
        toast(OCRresult)
    }

    //copy file to device
    private fun copyFiles() {
        try {
            val filepath = "$datapath/tessdata/kor.traineddata"
            val assetManager = assets
            val instream = assetManager.open("tessdata/kor.traineddata")
            val outstream = FileOutputStream(filepath)
            val buffer = ByteArray(1024)
            var read: Int

            do {
                read = instream.read(buffer)
                outstream.write(buffer, 0, read)
            } while (read != -1)

            outstream.flush()
            outstream.close()
            instream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun viewToBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(surface.width, surface.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        surface.setZOrderOnTop(true)
        surface.draw(canvas)
        surface.setZOrderOnTop(false)

        return bitmap
    }

    private fun initTess() {
        //이미지 디코딩을 위한 초기화
        image = viewToBitmap()
        //언어파일 경로
        datapath = filesDir.toString() + "/tesseract/"

        //트레이닝데이터가 카피되어 있는지 체크
        checkFile(File("$datapath/tessdata/"))

        mTess = TessBaseAPI()
        mTess.init(datapath, "kor")
        processImage()
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
            initTess()
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

    //권한 요청
    private fun askForPermission() {
        val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
        ActivityCompat.requestPermissions(this, permissions, resultPermission)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == resultPermission) {
            for (i in permissions.indices) {
                val permission = permissions[i]
                val grantResult = grantResults[i]

                if (permission == Manifest.permission.CAMERA && grantResult != PackageManager.PERMISSION_GRANTED) {
                    toast("접근 권한 허가가 필요합니다.")
                    finish()
                }
            }
        }
    }
}