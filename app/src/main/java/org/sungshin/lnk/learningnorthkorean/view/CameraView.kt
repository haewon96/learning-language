package org.sungshin.lnk.learningnorthkorean.view

import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException


class CameraView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    var mCamera: Camera
//    private val mCameraHolder: SurfaceHolder

    init {
        mCamera = Camera.open()
        mCamera.setDisplayOrientation(90)

//        mCameraHolder.addCallback(this)
//        mCameraHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mCamera.stopPreview()
        mCamera.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        try {
            mCamera.setPreviewDisplay(holder)
            mCamera.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}