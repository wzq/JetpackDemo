package com.wzq.sample

import android.content.Context
import androidx.startup.Initializer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.wzq.sample.service.CheckAccountWorker
import com.wzq.sample.service.WorkInitializer
import java.util.concurrent.TimeUnit

/**
 * create by wzq on 2020/11/5
 *
 */
// Initializes App.
class BaseInitializer : Initializer<Unit> {

    override fun create(context: Context) {
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(WorkInitializer::class.java)
    }
}
