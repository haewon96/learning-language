package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.sungshin.lnk.learningnorthkorean.R

class SituationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
