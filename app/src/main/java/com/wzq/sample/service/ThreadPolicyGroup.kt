package com.wzq.sample.service

import android.os.Handler
import android.os.HandlerThread
import androidx.core.content.ContextCompat
import com.wzq.sample.App
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * create by wzq on 2023/11/21
 *
 */
class ThreadPolicyGroup {
    companion object {
        val MainThread: Executor get() = ContextCompat.getMainExecutor(App.context)

        val IOThread: Executor by lazy {
            ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(), 1024,
                60L, TimeUnit.SECONDS,
                LinkedBlockingQueue()
            )
        }

        val WorkThread: Executor by lazy {
            LocalWorkThread().apply { start() }
        }
    }

    private class LocalWorkThread : HandlerThread("LocalWorkThread"), Executor {

        private var handler: Handler? = null
        override fun onLooperPrepared() {
            handler = Handler(looper)
        }

        override fun execute(command: Runnable?) {
            if (command != null) post(command)
        }

        private fun post(command: Runnable, delay: Long = 0) {
            if (handler == null) {
                throw Exception("LocalWorkThread --> handler is null")
            }
            if (!handler!!.postDelayed(command, delay)) {
                throw Exception("LocalWorkThread --> handler is shutting down")
            }
        }

        override fun quitSafely(): Boolean {
            handler?.removeCallbacksAndMessages(null)
            handler = null
            return super.quitSafely()
        }
    }
}

