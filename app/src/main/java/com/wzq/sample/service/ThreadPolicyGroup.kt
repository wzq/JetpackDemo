package com.wzq.sample.service

import android.os.Handler
import android.os.HandlerThread
import android.os.Process
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
object ThreadPolicyGroup {
    val mainThread: Executor by lazy { ContextCompat.getMainExecutor(App.context) }

    //level 0
    val workThread get() = LocalWorkThread.instance

    //level 5
    val ioThread: Executor by lazy {
        ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(), 1024,
            60L, TimeUnit.SECONDS,
            LinkedBlockingQueue()
        )
    }
}

class LocalWorkThread : HandlerThread, Executor {
    private constructor(name: String?) : this(name, Process.THREAD_PRIORITY_DEFAULT)
    private constructor(name: String?, priority: Int) : super(name, priority)

    companion object {
        val instance: LocalWorkThread by lazy {
            LocalWorkThread("LocalWorkThread").apply { start() }
        }
    }

    private var handler: Handler? = null
    override fun onLooperPrepared() {
        handler = Handler(looper)
    }

    override fun execute(command: Runnable?) {
        if (command != null) post(command)
    }

    fun post(command: Runnable, delay: Long = 0) {
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

