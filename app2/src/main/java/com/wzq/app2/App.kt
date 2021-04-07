package com.wzq.app2

import android.app.Application
import timber.log.Timber

/**
 * create by wzq on 2021/4/6
 *
 */
class App : Application() {

    companion object {
        lateinit var context: Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("app init")
        }
    }
}