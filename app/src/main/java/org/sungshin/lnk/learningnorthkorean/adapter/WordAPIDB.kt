package org.sungshin.lnk.learningnorthkorean.adapter

import android.provider.BaseColumns

object WordAPIDB : BaseColumns {
    val ID = "id"
    val TITLE = "title"
    val NTITLE = "ntitle"
    val STITLE = "stitle"
    val SEMANTIC = "semantic"
    val DSEMANTIC = "dsemantic"
    val SNGRAM = "sngram"
    val NGRAM = "ngram"
    val SGRAM = "sgram"
    val _TABLENAME = "wordAPITable"
    val _CREATE = ("create Table " + "(" + ID + " int primary key, "
            + TITLE + " string, "
            + NTITLE + " string, "
            + STITLE + " string, "
            + SEMANTIC + " string, "
            + DSEMANTIC + " string, "
            + SNGRAM + " string, "
            + NGRAM + " string, "
            + SGRAM + "string);")
}