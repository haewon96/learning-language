package org.sungshin.lnk.learningnorthkorean.util

class Translation(inputStr: String) {
    val inputStr: String = inputStr

    fun translate(): String {
        sliptStatement()

        return "번역 결과"
    }

    fun sliptStatement() {
        val inputList: List<String> = inputStr.split(".", " ")

        for (i in inputList) {
            println(inputList)
        }
    }
}