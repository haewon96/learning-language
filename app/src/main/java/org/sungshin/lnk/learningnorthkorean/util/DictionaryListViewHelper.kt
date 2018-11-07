package org.sungshin.lnk.learningnorthkorean.util

import org.sungshin.lnk.learningnorthkorean.Model.DictionaryListViewModel

class DictionaryListViewHelper {

    companion object {
        fun <ArrayList> getListViewModelList(): ArrayList {
            var listViewModelArrayList = ArrayList<DictionaryListViewModel>()
            listViewModelArrayList.add(DictionaryListViewModel(1, "우량하다", "우량하다"))
            listViewModelArrayList.add(DictionaryListViewModel(2, "우러나오다", "우러나오다"))
            listViewModelArrayList.add(DictionaryListViewModel(3, "우러러보다", "우러러보다"))
            listViewModelArrayList.add(DictionaryListViewModel(4, "우러르다", "우러르다"))
            listViewModelArrayList.add(DictionaryListViewModel(5, "우렁차다", "우렁차다"))
            listViewModelArrayList.add(DictionaryListViewModel(6, "우레", "우뢰"))
            listViewModelArrayList.add(DictionaryListViewModel(7, "우려", "우려"))
            listViewModelArrayList.add(DictionaryListViewModel(8, "우리", "우리"))
            listViewModelArrayList.add(DictionaryListViewModel(9, "우리", "우리"))
            listViewModelArrayList.add(DictionaryListViewModel(10, "우리", "우리"))
            listViewModelArrayList.add(DictionaryListViewModel(11, "우리들", ""))
            listViewModelArrayList.add(DictionaryListViewModel(12, "우리집", ""))
            listViewModelArrayList.add(DictionaryListViewModel(13, "우물", "우물"))
            listViewModelArrayList.add(DictionaryListViewModel(14, "우산", "우산"))




            return listViewModelArrayList as ArrayList
        }
    }
}