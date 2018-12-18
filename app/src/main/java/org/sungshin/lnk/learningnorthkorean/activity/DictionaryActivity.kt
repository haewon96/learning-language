package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_dictionary.*
import org.jetbrains.anko.startActivity
import org.sungshin.lnk.learningnorthkorean.adapter.DictionaryListViewAdapter
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.util.DictionaryListViewHelper
import org.sungshin.lnk.learningnorthkorean.util.WordAPIExplorer

class DictionaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView = findViewById<ListView>(R.id.lv_dictionary)
        val toggleLanguage = findViewById<Button>(R.id.btn_toggle_language)

        val apiExplorer:WordAPIExplorer = WordAPIExplorer(applicationContext)
        apiExplorer.execute()

        val wordList = DictionaryListViewHelper(applicationContext)
        wordList.initWordList()

        var listViewAdapter = DictionaryListViewAdapter(this, wordList.wordDBList)
        listView.adapter = listViewAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->  }

        toggleLanguage.setOnClickListener {view ->
            if (btn_toggle_language.text == "남한어") {
                btn_toggle_language.setText("북한어")
            } else {
                btn_toggle_language.setText("남한어")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dictionary, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            //R.id.menu_translate1 -> setRandomState()
        }
        when (item?.itemId) {
        R.id.action_scrap -> {
            //스크랩 버튼 눌렀을 때
            startActivity<DictionaryScrapActivity>()

            return super.onOptionsItemSelected(item)
        }
        else -> return super.onOptionsItemSelected(item)
    }
        return true
    }
}