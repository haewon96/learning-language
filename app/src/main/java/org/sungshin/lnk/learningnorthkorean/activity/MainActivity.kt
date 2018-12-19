package org.sungshin.lnk.learningnorthkorean.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.startActivity
import org.sungshin.lnk.learningnorthkorean.MapsActivity
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.adapter.WordAPIDBAdapter
import org.sungshin.lnk.learningnorthkorean.util.WordAPIExplorer

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        btn_main_movies.setOnClickListener(this)
        btn_main_translate.setOnClickListener(this)
        btn_main_situation.setOnClickListener(this)
        btn_main_quiz.setOnClickListener(this)
        btn_main_dictionary.setOnClickListener(this)


        //progressDialog(message = "Please wait a bit…", title = "Fetching data").show()

        var db = WordAPIDBAdapter(applicationContext).open()

        if (db.isEmpty) {
            //showProgress()
            val apiExplorer: WordAPIExplorer = WordAPIExplorer(applicationContext)
            apiExplorer.execute()
        }
        //hideProgress()
    }

    fun showProgress() {
        if (pd==null) {
            pd = ProgressDialog(this)

        }
        pd!!.setMessage("북한어 데이터를 불러오는 중입니다.")
        pd!!.show()
    }

    fun hideProgress() {
        if (pd != null && pd!!.isShowing) {
            pd!!.dismiss()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_main_movies ->
                startActivity<MoviesActivity>()
            R.id.btn_main_translate ->
                startActivity<TranslatorActivity>()
            R.id.btn_main_situation ->
                startActivity<MapsActivity>()
            R.id.btn_main_quiz ->
                startActivity<QuizActivity>()
            R.id.btn_main_dictionary ->
                startActivity<DictionaryActivity>()
        }
    }
}