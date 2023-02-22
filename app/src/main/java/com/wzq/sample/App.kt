package com.wzq.sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.NonUiContext
import androidx.annotation.UiContext
import timber.log.Timber

/**
 * create by wzq on 2021/4/6
 *
 */
class App : Application() {

    companion object {
        /**
         * App Context which is not about ui.
         */
        @SuppressLint("StaticFieldLeak")
        @NonUiContext
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("app init")
        }
    }
}