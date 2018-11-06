package org.sungshin.lnk.learningnorthkorean.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_trans_with_text.*
import org.sungshin.lnk.learningnorthkorean.R


class TransWithTextFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_trans_with_text, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        ib_trans_erase.setOnClickListener {
            clearText()
        }
    }

    private fun clearText() {
        et_trans_input.text = null
        tv_trans_output.text = getString(R.string.trans_output)
    }
}