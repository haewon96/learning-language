package org.sungshin.lnk.learningnorthkorean.util

class Translation(inputStr: String) {
    val inputStr: String = inputStr
    var resultStr = inputStr
    val gramArray = arrayOf("이", "가", "의", "을", "를", "에", "에게", "께", "한테",
            "에서", "에게서", "에서부터", "보다", "로", "으로", "로서", "로써", "으로서", "으로써",
            "에서", "에게서", "에서부터", "보다", "라고", "이라고", "와", "과", "랑", "이랑",
            "에", "같이", "처럼", "하고", "이며", "에다")

    fun translate(): String {
        sliptStatement()

        return resultStr
    }

    fun sliptStatement() {
        val inputList: List<String> = inputStr.split(".", " ")

        for (i in inputList) {
            println(inputList)
        }
    }

    fun replaceStatement() {

    }
}