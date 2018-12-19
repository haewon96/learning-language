package org.sungshin.lnk.learningnorthkorean.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.sungshin.lnk.learningnorthkorean.model.DictionaryListViewModel
import org.sungshin.lnk.learningnorthkorean.util.DictionaryListViewHelper

class DictionaryListViewAdapter(val context: Context, var wordList: ArrayList<Word>) : BaseAdapter(), Filterable {
//    var filterdList = ArrayList<Word>()
    lateinit var listFilter: Filter

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_dictionary_listview, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.southLanguage.text = wordList[position].stitle
        vh.northLanguage.text = wordList[position].ntitle

        return view
    }

    override fun getItem(position: Int): Any {
        return wordList[position]
    }

    fun getItemDBSTitle(position: Int): String? {
        return wordList[position].stitle
    }
    fun getItemDBNTitle(position: Int): String? {
        return wordList[position].ntitle
    }

    fun getItemDBSemantic(position: Int): String? {
        return wordList[position].semantic
    }

    fun getItemDBDSemantic(position: Int): String? {
        return wordList[position].dsemantic
    }

    fun getItemDBSGram(position: Int): String? {
        return wordList[position].sgram
    }

    fun getItemDBNGram(position: Int): String? {
        return wordList[position].ngram
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return wordList.size
    }

    private class ViewHolder(view: View?) {
        val southLanguage: TextView = view?.findViewById<TextView>(R.id.tv_south_korean) as TextView
        val northLanguage: TextView = view?.findViewById<TextView>(R.id.tv_north_korean) as TextView
    }

    override fun getFilter(): Filter {
        listFilter = ListFilter()

        return listFilter
    }

    inner class ListFilter : Filter() {
        override fun performFiltering(const: CharSequence?): FilterResults {
            val result = FilterResults()

            if (const == null || const.isEmpty()) {
                result.count = wordList.size
                result.values = wordList
            } else {
                val itemList = ArrayList<Word>()

                for (i in wordList) {
                    if (i.title!!.contains(const)) {
                        itemList.add(i)
                    }
                }

                result.values = itemList
                result.count = itemList.size
            }

            return result
        }

        override fun publishResults(const: CharSequence?, results: FilterResults?) {
            wordList = results?.values as ArrayList<Word>

            notifyDataSetChanged()
        }
    }
}

