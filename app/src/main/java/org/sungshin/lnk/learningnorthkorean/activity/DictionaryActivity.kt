package org.sungshin.lnk.learningnorthkorean.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.AdapterView
import android.widget.ListView
import org.sungshin.lnk.learningnorthkorean.Adapter.DictionaryListViewAdapter
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.util.DictionaryListViewHelper

class DictionaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView = findViewById<ListView>(R.id.lv_dictionary)

        var listViewAdapter = DictionaryListViewAdapter(this, DictionaryListViewHelper.Companion.getListViewModelList())
        listView.adapter = listViewAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->  }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dictionary, menu)

        return true
    }
}