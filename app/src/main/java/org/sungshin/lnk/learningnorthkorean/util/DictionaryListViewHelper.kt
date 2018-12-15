package org.sungshin.lnk.learningnorthkorean.util

import android.content.ContentValues.TAG
import android.util.Log
import org.sungshin.lnk.learningnorthkorean.`object`.App
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.sungshin.lnk.learningnorthkorean.activity.DictionaryActivity
import org.sungshin.lnk.learningnorthkorean.adapter.WordAPIDBAdapter
import org.sungshin.lnk.learningnorthkorean.model.DictionaryListViewModel


class DictionaryListViewHelper {
    var db = WordAPIDBAdapter(App.context()).open()
    companion object {
        fun <ArrayList> getListViewModelList(): ArrayList {

            //var apiExplorer = WordAPIExplorer
            /*var apiResultArray = ArrayList<Word>()
            apiResultArray = test.get()*/
                    //get()

            //var db = WordAPIDBAdapter(getListViewModelList()).writableDatabase


            val helper = WordAPIDBAdapter
            val mdb = helper.mDB
            val rdb = helper.rDB

            Log.d(TAG, "db 삽입 결과 : " + rdb.toString())

            var listViewModelArrayList = ArrayList<DictionaryListViewModel>()

            listViewModelArrayList.add(DictionaryListViewModel(1, "a", "aa"))
/*            for (i in 0..29) {
                listViewModelArrayList.add(DictionaryListViewModel(i, apiResultArray[i].stitle.toString(), apiResultArray[i].ntitle.toString()))
            }*/

            return listViewModelArrayList as ArrayList
        }
    }
}