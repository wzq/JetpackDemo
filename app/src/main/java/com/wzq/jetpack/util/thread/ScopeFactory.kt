package com.wzq.jetpack.util.thread

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


fun ioScope(ex: ((t: Throwable) -> Unit)? = null): CoroutineScope {

    val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        if (ex != null) {
            ex(throwable)
        }
    }

    return CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)
}
