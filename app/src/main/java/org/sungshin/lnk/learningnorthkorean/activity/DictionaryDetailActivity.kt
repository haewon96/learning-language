package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dictionary_detail.*
import org.sungshin.lnk.learningnorthkorean.R

class DictionaryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //val intent_semantic = intent.getIntent

        tv_south_korean.text = intent.extras["intentSTitle"].toString()
        tv_north_korean.text = intent.extras["intentNTitle"].toString()
        tv_dsemantic.text = intent.extras["intentDSemantic"].toString()
        tv_semantic.text = intent.extras["intentSemantic"].toString()
        tv_sgram.text = intent.extras["intentSGram"].toString()
        tv_ngram.text = intent.extras["intentNGram"].toString()
        //tv_semantic.setText(intent.getStringExtra("intentSemantic"))
    }
}