package com.wzq.sample.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wzq.sample.util.Prefs
import com.wzq.sample.util.PrefsSafety
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * create by wzq on 2021/4/22
 * It work on  [Dispatchers.Default].
 */
class CheckAccountWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(
    context, workerParams
) {
    var flag = 1
    override suspend fun doWork(): Result {
        return try {
            Prefs.set("work", "do work == ${Date()}")
            PrefsSafety.write("work", "do work == ${Date()}")
            println("${Thread.currentThread().name} do work ${flag++}")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

}