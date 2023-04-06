package com.wzq.sample.service

import android.content.Context
import androidx.work.*
import java.io.File

/**
 * create by wzq on 2023/4/3
 *
 */
class CrashRecordWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        fun start(context: Context, log: String) {
            if (log.toByteArray().size >= 10 * 1024) {
                return
            }
            val data = Data.Builder().putString("log", log).build()
            val request = OneTimeWorkRequestBuilder<CrashRecordWorker>().setInputData(data).build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork("crash_record", ExistingWorkPolicy.APPEND, request)
        }
    }

    override suspend fun doWork(): Result {
//        println("worker ---> crash log")
        val log = inputData.getString("log")
        if (log.isNullOrEmpty()) {
            return Result.failure()
        }
        return try {
            val filesDir = applicationContext.filesDir
            //todo 加密
            File(filesDir, "crash_log").appendText(log)
            Result.success()
        }catch (e: Exception) {
            Result.failure()
        }
    }
}