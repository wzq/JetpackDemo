package com.wzq.sample.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wzq.sample.util.Prefs
import java.util.*

/**
 * create by wzq on 2021/4/22
 *
 */
class CheckAccountWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(
    context, workerParams
) {
    var flag = 1
    override suspend fun doWork(): Result {
        return try {
            Prefs.set("work", "do work == ${Date().toLocaleString()}")
            //it will work in thread pool executor
            println("${Thread.currentThread().name} do work ${flag++}")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

}