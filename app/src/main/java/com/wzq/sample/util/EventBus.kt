package com.wzq.sample.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.drop

/**
 * create by wzq on 2023/4/18
 *
 */
object EventBus {

    private const val EXT_BUFFER = 1024 //2.shl(10)

    private val events by lazy {
        MutableSharedFlow<Any>(
            replay = 1,
            extraBufferCapacity = EXT_BUFFER,
            BufferOverflow.DROP_OLDEST
        )
    }

    suspend fun produceEvent(event: Any) {
        events.emit(event)
    }

    fun subscribe(replay: Boolean = true): Flow<Any> {
        val readOnly = events.asSharedFlow()
        readOnly.replayCache.apply {
            if (replay && this.isNotEmpty()) {
                readOnly.drop(this.size)
            }
        }
        return readOnly
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun clear() {
        events.resetReplayCache()
    }

}