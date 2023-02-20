package com.wzq.sample

import android.app.Application
import timber.log.Timber

/**
 * create by wzq on 2021/4/6
 *
 */
class App : Application() {

    companion object {
        private var app: Application? = null
        val instance: Application = app!!
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("app init")
        }
    }
}