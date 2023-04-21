package com.wzq.sample.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/4/18
 *
 */
object EventBus {

    private val localEvents = MutableSharedFlow<Event>() // private mutable shared flow
    val events = localEvents.asSharedFlow() // publicly exposed as read-only shared flow

    suspend fun produceEvent(event: Event) {
        localEvents.emit(event) // suspends until all subscribers receive it
    }

    fun produceEvent(scope: CoroutineScope, event: Event) {
        scope.launch {
            localEvents.emit(event)
        }
    }
}

data class Event(
    val id: Int = -1, val tag: String? = null, val data: Any?
)