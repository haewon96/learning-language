package org.sungshin.lnk.learningnorthkorean.activity

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_translate.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.util.Translation
import org.sungshin.lnk.learningnorthkorean.util.WordAPIExplorer
import java.util.*
import android.text.Spannable
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.util.Log.d
import android.widget.TextView


class TranslatorActivity : AppCompatActivity() {
    private val resultPermission = 100
    private val NKToSK = 0
    private val SKToNK = 1
    private val startSST = 2
    private val startTakingPic = 3
    private var state = SKToNK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)
        initView()

        askForPermission()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        ib_trans_paste.setOnClickListener {
            if (clipboard.primaryClip != null) {
                val textToPaste = clipboard.primaryClip.getItemAt(0).text
                et_trans_input.setText(et_trans_input.text.toString() + textToPaste)
            }
        }

        ib_trans_erase.setOnClickListener {
            et_trans_input.text = null
        }

        ib_trans_copy.setOnClickListener {
            val clip = ClipData.newPlainText(null, tv_trans_output.text)
            clipboard.primaryClip = clip
        }

        ib_trans_exchange.setOnClickListener { changeLanguage() }

        ib_camera.setOnClickListener { startActivityForResult<RecognizeTextActivity>(startTakingPic) }
        ib_voice.setOnClickListener { startSST() }

        btn_trans_start.setOnClickListener {
            toggleProgress()
            refreshView()
        }

        et_trans_input.movementMethod = ScrollingMovementMethod.getInstance() // 스크롤 설정
    }

    private fun refreshView() {
        val trans = Translation(et_trans_input.text.toString())
        val explorer = WordAPIExplorer
        val indexArray = trans.translate()
        tv_trans_output.setText(et_trans_input.text, TextView.BufferType.SPANNABLE)
        val span: Spannable = tv_trans_output.text as Spannable

        for (i in indexArray) {
            d("index", i.start.toString() + " " + i.end)
            span.setSpan(BackgroundColorSpan(resources.getColor(R.color.colorHighlight)), i.start, i.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        toggleProgress()
    }

    private fun changeLanguage() {
        state = if (state == SKToNK) NKToSK else SKToNK

        // 문자열 교체
        tv_trans_korean_title1.text = getString(if (state == SKToNK) R.string.all_south_korean else R.string.all_north_korean)
        tv_trans_korean_title2.text = getString(if (state == SKToNK) R.string.all_north_korean else R.string.all_south_korean)
        tv_trans_korean1.text = getString(if (state == SKToNK) R.string.all_south_korean else R.string.all_north_korean)
        tv_trans_korean2.text = getString(if (state == SKToNK) R.string.all_north_korean else R.string.all_south_korean)

        val temp = et_trans_input.text
        et_trans_input.setText(tv_trans_output.text)
        tv_trans_output.text = temp
    }

    private fun toggleProgress() {
        trans_progress.visibility = if (trans_progress.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }

    private fun setRandomState() {
        val randomArray = resources.getStringArray(
                if (state == SKToNK) R.array.state_south else R.array.state_north)
        val resultArray = resources.getStringArray(
                if (state == NKToSK) R.array.state_south else R.array.state_north)
        val index = Random().nextInt(randomArray.size)

        et_trans_input.setText(randomArray[index])
//        tv_trans_output.text = resultArray[index]
    }

    private fun startSST() {
        if (isConnected()) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            startActivityForResult(intent, startSST)
        } else
            toast(R.string.all_network_err)
    }

    // 네트워크 연결 여부
    private fun isConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val net = cm.activeNetworkInfo
        return (net != null && net.isConnected)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recommend, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.menu_translate1 -> setRandomState()
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == startSST && resultCode == Activity.RESULT_OK)
            et_trans_input.setText(data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0))
        else if (requestCode == startTakingPic && resultCode == Activity.RESULT_OK) {

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    //권한 요청
    private fun askForPermission() {
        val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
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