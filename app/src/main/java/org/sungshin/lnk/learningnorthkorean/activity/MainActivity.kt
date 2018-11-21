package org.sungshin.lnk.learningnorthkorean.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.sungshin.lnk.learningnorthkorean.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        btn_main_movies.setOnClickListener(this)
        btn_main_translate.setOnClickListener(this)
        btn_main_situation.setOnClickListener(this)
        btn_main_quiz.setOnClickListener(this)
        btn_main_dictionary.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_main_movies ->
                startActivity<MoviesActivity>()
            R.id.btn_main_translate ->
                startActivity<TranslatorActivity>()
            R.id.btn_main_situation ->
                startActivity<SituationActivity>()
            R.id.btn_main_quiz ->
                startActivity<QuizActivity>()
            R.id.btn_main_dictionary ->
                startActivity<DictionaryActivity>()
        }
    }
}