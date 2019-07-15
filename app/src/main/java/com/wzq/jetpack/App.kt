package com.wzq.jetpack

import android.app.Application
import com.wzq.jetpack.BuildConfig
import timber.log.Timber

/**
 * Created by wzq on 2019-07-12
 *
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}