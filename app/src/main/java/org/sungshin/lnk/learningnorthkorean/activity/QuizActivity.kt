package org.sungshin.lnk.learningnorthkorean.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.sungshin.lnk.learningnorthkorean.MapsActivity
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Problem
import java.util.ArrayList

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_level1.setOnClickListener(this)
        btn_level2.setOnClickListener(this)
        btn_level3.setOnClickListener(this)
        btn_level4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_level1 ->
                startActivity<QuizLevel1Activity>()
        //startActivityForResult<QuizExecuteActivity>("intentLevel" to intentLevel1, "intent" to intent)
            R.id.btn_level2 ->
                startActivity<QuizLevel2Activity>()
            R.id.btn_level3 ->
                startActivity<QuizLevel3Activity>()
            R.id.btn_level4 ->
                startActivity<QuizLevel4Activity>()
        }
    }
}
