package org.sungshin.lnk.learningnorthkorean.`object`

import android.app.Application
import android.content.Context

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }
}
/*
class App : Application() {
    companion object {
        var instance: App? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext
}*/
