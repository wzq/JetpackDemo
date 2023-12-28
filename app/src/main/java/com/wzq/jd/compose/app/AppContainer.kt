package com.wzq.jd.compose.app

import androidx.room.Room
import com.wzq.jd.compose.app.data.local.AppDatabase

/**
 * create by wzq on 2023/12/28
 *
 */
object AppContainer {

    val networkMonitor by lazy {
        ConnectivityManagerNetworkMonitor(App.context)
    }

    val database by lazy {
        Room.databaseBuilder(App.context, AppDatabase::class.java, "database-j")
            .fallbackToDestructiveMigration()
            .build()
    }

    private var initialized = false
    fun preInit(){
        if (initialized) {
            return
        }
        networkMonitor
        database
        initialized = true
    }
}