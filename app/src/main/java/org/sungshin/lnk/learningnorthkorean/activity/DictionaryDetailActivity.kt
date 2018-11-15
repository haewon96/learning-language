package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.sungshin.lnk.learningnorthkorean.R

class DictionaryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}