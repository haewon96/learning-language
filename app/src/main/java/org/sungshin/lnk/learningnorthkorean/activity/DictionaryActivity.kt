package org.sungshin.lnk.learningnorthkorean.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_dictionary.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.sungshin.lnk.learningnorthkorean.adapter.DictionaryListViewAdapter
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.adapter.WordAPIDBAdapter
import org.sungshin.lnk.learningnorthkorean.util.DictionaryListViewHelper
import org.sungshin.lnk.learningnorthkorean.util.WordAPIExplorer
import android.text.Editable
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_translate.*
import org.w3c.dom.Text
import java.util.*


class DictionaryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView = findViewById<ListView>(R.id.lv_dictionary)
        val toggleLanguage = findViewById<Button>(R.id.btn_toggle_language)

/*        var db = WordAPIDBAdapter(applicationContext).open()

        if (db.isEmpty) {
            val apiExplorer: WordAPIExplorer = WordAPIExplorer(applicationContext)
            apiExplorer.execute()
        }*/

        val wordList = DictionaryListViewHelper(applicationContext)
        wordList.initWordList()

        var listViewAdapter = DictionaryListViewAdapter(this, wordList.wordDBList)
        listView.adapter = listViewAdapter
        //listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id -> }
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, DictionaryDetailActivity::class.java)
            intent.putExtra("intentSTitle", listViewAdapter.getItemDBSTitle(position))
            intent.putExtra("intentNTitle", listViewAdapter.getItemDBNTitle(position))
            intent.putExtra("intentSemantic", listViewAdapter.getItemDBSemantic(position))
            intent.putExtra("intentDSemantic", listViewAdapter.getItemDBDSemantic(position))
            intent.putExtra("intentSGram", listViewAdapter.getItemDBSGram(position))
            intent.putExtra("intentNGram", listViewAdapter.getItemDBNGram(position))
            startActivity(intent)
        }

        listViewAdapter.notifyDataSetChanged()


        toggleLanguage.setOnClickListener { view ->
            if (btn_toggle_language.text == "남한어") {
                btn_toggle_language.setText("북한어")
            } else {
                btn_toggle_language.setText("남한어")
            }
        }

/*        et_search!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val text = et_search!!.text.toString()
                search(text)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })*/

        //val editTextFilter = findViewById<View>(R.id.et_search) as EditText
/*        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable) {
                val filterText = edit.toString()
                if (filterText.isNotEmpty()) {
                    listView.setFilterText(filterText)
                } else {
                    listView.clearTextFilter()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })*/

/*        et_search.addTextChangedListener(TextWatcher {
            @Override
            fun afterTextChanged(Editable edit) {

            }
        })*/

       btn_search.setOnClickListener {
            val keyword = et_search.text.toString()

            if (keyword.isEmpty()) {
                toast("검색어를 입력해주세요.")
                listView.clearTextFilter()
            } else {
                listViewAdapter.filter.filter(keyword)
            }
        }
    }

    private fun setRandomStateSearch() {
        val randomArray = resources.getStringArray(R.array.state_search)
        val index = Random().nextInt(randomArray.size)
        et_search.setText(randomArray[index])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dictionary, menu)

        return true
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.action_dictionary_recommend -> setRandomStateSearch()
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