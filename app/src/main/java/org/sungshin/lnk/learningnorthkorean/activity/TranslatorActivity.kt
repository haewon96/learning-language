package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.fragment.TransWithTextFragment

class TranslatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadFragment(TransWithTextFragment())

//        bnve_translate.onNavigationItemSelectedListener()
    }

    fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, "fragment")
                .commit()

        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()

        return super.onOptionsItemSelected(item)
    }
}
