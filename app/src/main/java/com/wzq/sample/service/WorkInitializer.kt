package com.wzq.sample.service

import android.content.Context
import androidx.startup.Initializer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * create by wzq on 2021/4/22
 *
 */
class WorkInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        val request = PeriodicWorkRequestBuilder<CheckAccountWorker>(15, TimeUnit.MINUTES)
            .build()

        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniquePeriodicWork(
            "test",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
        workManager.getWorkInfoByIdLiveData(request.id).observeForever {
        }

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        TODO("Not yet implemented")
    }
}