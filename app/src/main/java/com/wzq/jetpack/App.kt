package com.wzq.jetpack

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import com.wzq.jetpack.BuildConfig
import timber.log.Timber

/**
 * Created by wzq on 2019-07-12
 *
 */
class App : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}