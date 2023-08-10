package com.wzq.sample

import com.wzq.sample.util.EventBus
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * create by wzq on 2023/8/1
 *
 */
class TestBus {


    @Test
    fun testE(): Unit {
        runTest {
            val f1 = channelFlow {
                send(1)
                send(2)
            }

            val f2 = flow {
                emit("a")
                emit("b")
            }

            combine(f1,f2) {
                f1, f2 -> "$f1 + $f2"
            }.collectLatest {
                println(it)
            }
        }
    }

    @Test
    fun test() = runTest {
        launch {
            repeat(5) {
                EventBus.produceEvent("before -- $it")
            }
            cancel()
        }

        delay(1000)

        val job1 = launch {
            EventBus.subscribe(false).onEach {
                println("job1 -> $it")
            }.collect()
        }

        val job2 = launch {
            EventBus.subscribe().collectLatest {
                println("job2 -> $it")
            }
        }

        launch {
            repeat(10) {
                delay(1000)
                EventBus.produceEvent("after -- $it")
                println("send$it")
            }
            cancel()
        }



        delay(10000)

        job1.cancel()
        job2.cancel()

    }
}