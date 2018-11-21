package org.sungshin.lnk.learningnorthkorean.util

import android.os.AsyncTask
import org.sungshin.lnk.learningnorthkorean.`object`.Word
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL
import java.net.URLEncoder

object WordAPIExplorer : AsyncTask<String, Void, String>() {
    var factory: XmlPullParserFactory? = null
    var parser: XmlPullParser? = null
    val urlBuilder = StringBuilder("http://openapi.korean.go.kr/openapi/service/SouthNorthWordsService/getSouthNorthWordsList")
    val key = "K2s1iriwI3Kh8htNQmvI8UjqBMSBHXZPufkIl0QBC1yVxxC7DrN8sNmFynz71dk2u9C7%2BSWSU0Oq3cix3Y61IA%3D%3D"
    var result = ""
    var resultArray: ArrayList<Word> = arrayListOf()
    private lateinit var url: URL

    init {
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key) // Service Key
    }

    fun setSearchUrl(word: String) {
        urlBuilder.append("&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(word, "UTF-8"))
    }

    override fun doInBackground(vararg p0: String?): String {
        var flag = false

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
                        if (parser?.name.equals("title")) {
                            flag = true
                        }
                    }

                    XmlPullParser.TEXT -> {
                        if (flag) {
                            result += parser?.text + "\n"
                            flag = false
                        }
                    }
                }

                eventType = parser?.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "err"
        }

        return result
    }
}