package org.sungshin.lnk.learningnorthkorean.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sungshin.lnk.learningnorthkorean.R

class KaesongFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val kaesongFragmentView: View = inflater!!.inflate(R.layout.fragment_kaesong, container, false)

        return kaesongFragmentView
    }
}