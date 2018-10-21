package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_translate.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.fragment.TransWithPicFragment
import org.sungshin.lnk.learningnorthkorean.fragment.TransWithTextFragment
import org.sungshin.lnk.learningnorthkorean.fragment.TransWithVoiceFragment

class TranslatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadFragment(TransWithTextFragment())

        bnve_translate.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_translate1 -> loadFragment(TransWithTextFragment())
                R.id.menu_translate2 -> loadFragment(TransWithPicFragment())
                R.id.menu_translate3 -> loadFragment(TransWithVoiceFragment())
                else -> loadFragment(TransWithTextFragment())
            }
        }
    }

    //fragment 교체 함수
    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, "fragment")
                .commit()

        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()

        return super.onOptionsItemSelected(item)
    }
}