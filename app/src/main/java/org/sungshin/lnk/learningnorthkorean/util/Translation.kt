package org.sungshin.lnk.learningnorthkorean.util

import android.util.Log
import org.sungshin.lnk.learningnorthkorean.`object`.WordIndex

class Translation(val inputStr: String) {
    val inputList: List<String> = inputStr.split(".", " ")
    val indexList: ArrayList<WordIndex> = arrayListOf()

    val gramArray = arrayOf("한테", "에서", "에게서", "에서부터", "보다",
            "으로", "로서", "로써", "으로서", "으로써",
            "라고", "이라고", "이랑", "같이", "처럼", "하고", "이며", "에다",
            "이", "가", "의", "을", "를", "에", "에게", "께", "로", "와", "과", "랑")

    fun translate(): ArrayList<WordIndex> {
        for (i in inputList) {
            loop@ for (j in gramArray) {
                if (i.contains(j) && i.length > 2) { // 단어가 조사를 포함하고 있을 경우
                    val start = inputStr.indexOf(i)
                    indexList.add(WordIndex(start, start + i.length - j.length))
                    Log.d("index word", i.substring(0, i.length - j.length))
                    break@loop
                }
            }
        }

        return indexList
    }

    fun replaceStatement() {

    }
}