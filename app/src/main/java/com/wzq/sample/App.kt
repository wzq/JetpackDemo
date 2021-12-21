package com.wzq.sample

import android.app.Application
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat
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


    private fun getScreenInfo() {
        ContextCompat.getSystemService(this, WindowManager::class.java)?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            this.defaultDisplay
        }
    }
}