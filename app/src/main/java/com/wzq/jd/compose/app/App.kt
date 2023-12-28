package com.wzq.jd.compose.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * create by wzq on 2023/12/20
 *
 */
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var _context: Context? = null

        val context: Context get() = _context!!

    }

    override fun onCreate() {
        super.onCreate()
        _context = this.applicationContext

        AppContainer.preInit()
    }
}