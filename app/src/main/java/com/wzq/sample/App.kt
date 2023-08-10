package com.wzq.sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Looper
import androidx.annotation.NonUiContext
import com.wzq.sample.util.FileUtils
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
        Looper.getMainLooper().thread.setUncaughtExceptionHandler { t, e ->
            //record ex log
            val logFile = File(applicationContext.filesDir, "crash_log_${System.currentTimeMillis()}")
            val log = t.name + "---" + Date().toString() + " \n" + e.printStackTrace()
            FileUtils.writeEncryptedFile(applicationContext, logFile, log)
            //let it crash
            Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(t, e)
        }
    }
}