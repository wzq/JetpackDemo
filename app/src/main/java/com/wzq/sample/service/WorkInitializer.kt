package com.wzq.sample.service

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * create by wzq on 2021/4/22
 *
 */
class WorkInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        initWorker(context)

        val request = PeriodicWorkRequestBuilder<CheckAccountWorker>(
            15L, TimeUnit.MINUTES
        ).build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "w1", ExistingPeriodicWorkPolicy.KEEP, request
        )
    }

    /**
     * 注意：如果手动初始化worker，则需要禁用manifest文件里的配置
     *
     * worker api < 2.6
     * ```
     *     <provider
     *          android:name="androidx.work.impl.WorkManagerInitializer"
     *          android:authorities="${applicationId}.workmanager-init"
     *          tools:node="remove" />
     * ```
     * work api >= 2.6
     *
     * ```
     *            <meta-data
     *               android:name="androidx.work.WorkManagerInitializer"
     *                android:value="androidx.startup"
     *              tools:node="remove" />
     * ```
     * > 因为work本身也是通过content provider初始化的
     */
    private fun initWorker(context: Context) {
        val workConfig = Configuration.Builder().build()
        WorkManager.initialize(context, workConfig)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}