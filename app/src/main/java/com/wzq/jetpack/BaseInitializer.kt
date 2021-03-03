package com.wzq.jetpack

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

/**
 * create by wzq on 2020/11/5
 *
 */
// Initializes App.
class BaseInitializer : Initializer<Unit> {

    companion object {
        lateinit var app: Context
    }

    override fun create(context: Context) {
        app = context
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("app init")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
