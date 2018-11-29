package org.sungshin.lnk.learningnorthkorean.util

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL
import java.net.URLEncoder

object WordAPIExplorer : AsyncTask<String, Void, ArrayList<Word>>() {
    var factory: XmlPullParserFactory? = null
    var parser: XmlPullParser? = null
    val urlBuilder = StringBuilder("http://openapi.korean.go.kr/openapi/service/SouthNorthWordsService/getSouthNorthWordsList")
    val key = "K2s1iriwI3Kh8htNQmvI8UjqBMSBHXZPufkIl0QBC1yVxxC7DrN8sNmFynz71dk2u9C7%2BSWSU0Oq3cix3Y61IA%3D%3D"
    var result:String = ""
    var resultArray: ArrayList<Word> = arrayListOf()
    private lateinit var url: URL

    init {
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key + "&numOfRows=50") // Service Key
    }

    fun setSearchUrl(word: String) {
        urlBuilder.append("&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(word, "UTF-8"))
    }

    override fun doInBackground(vararg p0: String?): ArrayList<Word> {
        var flag_id = false
        var flag_title = false
        var flag_ntitle = false
        var flag_stitle = false
        var flag_semantic = false
        var flag_dsemantic = false
        var flag_sngram = false
        var flag_sgram = false
        var flag_ngram = false
        var temp_id:Int = 0
        var temp_title:String? = ""
        var temp_ntitle:String? = ""
        var temp_stitle:String? = ""
        var temp_semantic:String? = ""
        var temp_dsemantic:String? = ""
        var temp_sngram:String? = ""
        var temp_ngram:String? = ""
        var temp_sgram:String? = ""
        var word:Word
        var i:Int = 0

        try {
            url = URL(urlBuilder.toString())
            url.openConnection().getInputStream()
            factory = XmlPullParserFactory.newInstance()
            parser = factory?.newPullParser()
            parser?.setInput(url.openStream(), "utf-8")
            var eventType = parser?.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (parser?.name.equals("id")) {
                            flag_id = true
                        }
                        if (parser?.name.equals("dsemantic")) {
                            flag_dsemantic = true
                        }
                        if (parser?.name.equals("ngram")) {
                            flag_ngram = true
                        }
                        if(parser?.name.equals("ntitle")) {
                            flag_ntitle = true
                        }
                        if (parser?.name.equals("semantic")) {
                            flag_semantic = true
                        }
                        if (parser?.name.equals("sgram")) {
                            flag_sgram = true
                        }
                        if (parser?.name.equals("sngram")) {
                            flag_sngram = true
                        }
                        if (parser?.name.equals("stitle")) {
                            flag_stitle = true
                        }
                        if (parser?.name.equals("title")) {
                            flag_title = true
                        }
                    }

                    XmlPullParser.TEXT -> {
                        if (flag_id) {
                            temp_id = parser?.text!!.toInt()
                            flag_id = false
                        }
                        if (flag_dsemantic) {
                            temp_dsemantic = parser?.text
                            flag_dsemantic = false
                        }
                        if (flag_ngram) {
                            temp_ngram = parser?.text
                            flag_ngram = false
                        }
                        if (flag_ntitle) {
                            temp_ntitle = parser?.text
                            flag_ntitle = false
                        }
                        if (flag_semantic) {
                            temp_semantic = parser?.text
                            flag_semantic = false
                        }
                        if (flag_sgram) {
                            temp_sgram = parser?.text
                            flag_sgram = false
                        }
                        if (flag_sngram) {
                            temp_sngram = parser?.text
                            flag_sngram = false
                        }
                        if (flag_stitle) {
                            temp_stitle = parser?.text
                            flag_stitle = false
                        }
                        if (flag_title) {
                            temp_title = parser?.text
                            flag_title = false
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if (parser?.name.equals("item")) {
                            word = Word(temp_id, temp_title, temp_ntitle, temp_stitle, temp_semantic, temp_dsemantic, temp_sngram, temp_ngram, temp_sgram)
                            resultArray.add(word)
                            temp_title = "-"
                            temp_ntitle = "-"
                            temp_stitle = "-"
                            temp_semantic = "-"
                            temp_dsemantic = "-"
                            temp_sngram = "-"
                            temp_ngram = "-"
                            temp_sgram = "-"
                        }
                    }
                }

                eventType = parser?.next()

                i++
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "err"
        }

        return resultArray
    }
}