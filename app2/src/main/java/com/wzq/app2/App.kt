package com.wzq.app2

import android.app.Application

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
    }
}