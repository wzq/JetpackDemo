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
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        return try {
            Prefs.set("work","do work == ${Date().toLocaleString()}")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

}