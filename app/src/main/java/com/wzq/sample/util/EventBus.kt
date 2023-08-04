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

    private const val REPLAY_SIZE = 1
    private const val EXT_BUFFER = 2.shl(10)

    private val events =
        MutableSharedFlow<Any>(
            replay = REPLAY_SIZE,
            extraBufferCapacity = EXT_BUFFER,
            BufferOverflow.DROP_OLDEST
        ) //

    suspend fun produceEvent(event: Any) {
        events.emit(event)
    }

    fun subscribe(isSticky: Boolean = true): Flow<Any> =
        events.asSharedFlow().let { flow ->
            if (!isSticky && flow.replayCache.isNotEmpty()) {
                flow.drop(REPLAY_SIZE)
            } else {
                flow
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun clear() {
        events.resetReplayCache()
    }

}