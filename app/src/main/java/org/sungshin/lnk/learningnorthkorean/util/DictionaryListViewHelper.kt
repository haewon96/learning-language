package org.sungshin.lnk.learningnorthkorean.util

import android.content.ContentValues.TAG
import android.util.Log
import org.jetbrains.anko.doAsyncResult
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.sungshin.lnk.learningnorthkorean.model.DictionaryListViewModel


class DictionaryListViewHelper {

    companion object {
        fun <ArrayList> getListViewModelList(): ArrayList {

            val test = WordAPIExplorer.execute()
            var apiExplorer = WordAPIExplorer
            var apiResultArray = ArrayList<Word>()
            apiResultArray = test.get()

            var listViewModelArrayList = ArrayList<DictionaryListViewModel>()

            for (i in 0..29) {
                listViewModelArrayList.add(DictionaryListViewModel(i, apiResultArray[i].stitle.toString(), apiResultArray[i].ntitle.toString()))
            }

            return listViewModelArrayList as ArrayList
        }
    }
}