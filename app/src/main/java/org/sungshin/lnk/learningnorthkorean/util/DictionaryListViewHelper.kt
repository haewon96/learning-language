package org.sungshin.lnk.learningnorthkorean.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import org.sungshin.lnk.learningnorthkorean.`object`.App
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.sungshin.lnk.learningnorthkorean.activity.DictionaryActivity
import org.sungshin.lnk.learningnorthkorean.adapter.WordAPIDBAdapter
import org.sungshin.lnk.learningnorthkorean.model.DictionaryListViewModel


class DictionaryListViewHelper(val context: Context) {
    var db = WordAPIDBAdapter(context).open()
    val db_id = db.getID()
    val db_title = db.getTitle()
    val db_ntitle = db.getNTitle()
    val db_stitle = db.getSTitle()
    val db_semantic = db.getSemantic()
    val db_dsemantic = db.getDSemantic()
    val db_sngram = db.getSNGram()
    val db_ngram = db.getNGram()
    val db_sgram = db.getSGram()

    var db_iterator = db_id.size.toInt()
    val wordDBList = ArrayList<Word>()

    fun initWordList() {
        for (i in 0 until db_iterator) {
            wordDBList.add(Word(
                    db_id[i],
                    db_title[i],
                    db_ntitle[i],
                    db_stitle[i],
                    db_semantic[i],
                    db_dsemantic[i],
                    db_sngram[i],
                    db_ngram[i],
                    db_sgram[i]
            ))

            Log.d("사전 단어", db_stitle[i] + " " + db_ntitle[i])
        }
    }
}