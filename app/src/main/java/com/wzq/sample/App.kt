package com.wzq.sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.NonUiContext
import timber.log.Timber
import java.io.File
import java.util.*

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
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("app init")
            crashReport()
        }
    }

    private fun crashReport() {
        Thread.currentThread().setUncaughtExceptionHandler { t, e ->
            //record ex log
            File(filesDir, "crash_log").appendText(
                t.name + "---" + Date().toString() + " \n" + e.stackTraceToString()
            )
            //let it crash
            Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(t, e)
        }
    }
}