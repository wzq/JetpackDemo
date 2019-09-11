package com.wzq.jetpack.util.thread

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


fun IOScope(ex: ((t: Throwable) -> Unit)? = null): CoroutineScope {

    val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        if (ex != null) {
            ex(throwable)
        }
    }

    return CoroutineScopeImpl(Dispatchers.IO + SupervisorJob() + exceptionHandler)
}

class CoroutineScopeImpl(override val coroutineContext: CoroutineContext) : CoroutineScope


