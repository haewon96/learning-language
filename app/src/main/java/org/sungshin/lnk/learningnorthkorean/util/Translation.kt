package org.sungshin.lnk.learningnorthkorean.util

class Translation(inputStr: String) {
    val inputStr: String = inputStr
    var resultStr = ""
    val inputList: List<String> = inputStr.split(".", " ")

    val gramArray = arrayOf("이", "가", "의", "을", "를", "에", "에게", "께", "한테",
            "에서", "에게서", "에서부터", "보다", "로", "으로", "로서", "로써", "으로서", "으로써",
            "라고", "이라고", "와", "과", "랑", "이랑", "같이", "처럼", "하고", "이며", "에다")

    fun translate(): String {
        for (i in inputList) {
            loop@ for (j in gramArray) {
                if (i.contains(j)) {
                    val start = i.indexOf(j)
                    val end = start + j.length
                    resultStr += "\'" + i + "\'" + " "
                    break@loop
                }
            }
        }

        return resultStr
    }

    fun replaceStatement() {

    }
}