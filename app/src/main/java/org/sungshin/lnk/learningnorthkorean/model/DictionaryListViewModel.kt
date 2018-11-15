package org.sungshin.lnk.learningnorthkorean.model

class DictionaryListViewModel {
    var id: Int? = null
    var southLanguage: String? = null
    var northLanguage: String? = null

    constructor(id: Int, southLanguage: String, northLanguage: String) {
        this.id = id
        this.southLanguage = southLanguage
        this.northLanguage = northLanguage
    }
}