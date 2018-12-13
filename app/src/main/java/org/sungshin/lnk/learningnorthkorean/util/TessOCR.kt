package org.sungshin.lnk.learningnorthkorean.util

import android.content.res.AssetManager
import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class TessOCR(val filesDir: File, val assetManager: AssetManager, val bitmap: Bitmap) {
    lateinit var mTess: TessBaseAPI
    var datapath = "" //언어 데이터 경로

    fun initTess() {
        datapath = filesDir.toString()

        checkFile(File("$datapath/tessdata/"))

        mTess = TessBaseAPI()
        mTess.init(datapath, "kor")
    }

    fun processImage(): String {
        mTess.setImage(bitmap)

        return mTess.utF8Text
    }

    fun checkFile(dir: File) {
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles()
        }
        if (dir.exists()) {
            val datafile = File("$datapath/tessdata/kor.traineddata")
            if (!datafile.exists()) {
                copyFiles()
            }
        }
    }

    private fun copyFiles() {
        try {
            val filepath = "$datapath/tessdata/kor.traineddata"
            val instream = assetManager.open("tessdata/kor.traineddata")
            val outstream = FileOutputStream(filepath)
            val buffer = ByteArray(1024)
            var read: Int

            do {
                read = instream.read(buffer)
                outstream.write(buffer, 0, read)
            } while (read != 0)

            outstream.flush()
            outstream.close()
            instream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}