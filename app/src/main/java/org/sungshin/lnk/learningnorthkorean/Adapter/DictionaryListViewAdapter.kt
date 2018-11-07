package org.sungshin.lnk.learningnorthkorean.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.Model.DictionaryListViewModel

class DictionaryListViewAdapter(val context: Context, val listModelArrayList: ArrayList<DictionaryListViewModel>) : BaseAdapter() {

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

        vh.southLanguage.text = listModelArrayList[position].southLanguage
        vh.northLanguage.text = listModelArrayList[position].northLanguage
        return view
    }

    override fun getItem(position: Int): Any {
        return listModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listModelArrayList.size
    }

    private class ViewHolder(view: View?) {
        val southLanguage: TextView = view?.findViewById<TextView>(R.id.tv_south_korean) as TextView
        val northLanguage: TextView = view?.findViewById<TextView>(R.id.tv_north_korean) as TextView
    }
}
