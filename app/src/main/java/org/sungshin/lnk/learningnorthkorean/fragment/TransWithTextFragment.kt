package org.sungshin.lnk.learningnorthkorean.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_trans_with_text.*
import org.sungshin.lnk.learningnorthkorean.R
import org.sungshin.lnk.learningnorthkorean.util.Translation


class TransWithTextFragment : Fragment() {
    val NKToSK = 0
    val SKToNK = 1
    private var state = NKToSK

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_trans_with_text, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        ib_trans_erase.setOnClickListener { clearText() }

        ib_trans_exchange.setOnClickListener { changeLanguage() }

        btn_trans_start.setOnClickListener {
            toggleProgress()
            refreshView()
        }
    }

    private fun refreshView() {
        val trans = Translation(et_trans_input.text.toString())

        tv_trans_output.text = trans.translate()
        toggleProgress()
    }

    private fun clearText() {
        et_trans_input.text = null
        tv_trans_output.text = getString(R.string.trans_output)
    }

    private fun changeLanguage() {
        state = if (state == SKToNK) NKToSK else SKToNK

        // 문자열 교체
        tv_trans_korean_title1.text = getString(if (state == SKToNK) R.string.all_south_korean else R.string.all_north_korean)
        tv_trans_korean_title2.text = getString(if (state == SKToNK) R.string.all_north_korean else R.string.all_south_korean)
    }

    private fun toggleProgress() {
        trans_progress.visibility = if (trans_progress.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }
}