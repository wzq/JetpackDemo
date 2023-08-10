package com.wzq.sample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * create by wzq on 2023/5/8
 *
 */
class KTest {

    @Test
    fun test() = runTest {
        GlobalScope.launch {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

            scope.launch {
                async {
                    println(1)
                    throw RuntimeException("task1 failed")
                }.join()
                async {
                    println(3)
                    println(33)
                }.join()
                async {
                    println(4)
                    println(44)
                }.join()
            }
        }
    }

    @Test
    fun test1() = runTest {
        flow<Int> {
            (1..10).forEach { emit(it) }
        }.collectLatest {
            println(it)
        }
        println("end")
    }

    @Test
    fun test2() = runTest {
        supervisorScope {
            launch {
                throw Error("test error1")
            }
            launch {
                println(1)
            }
        }

        coroutineScope {
            val job = SupervisorJob()
            launch(job) {
                throw Error("test error2")
            }
            launch(job) {
                println(2)
            }
        }
    }

    @Test //inline noinline cross-inline
    fun test3() {
        f1 {
            println(200.toShort())
            return@f1
        }
    }

    inline fun f1(crossinline block: () -> Unit) {
        f2 {
            block()
        }
    }

    fun f2(block: () -> Unit) {
        block()
    }


    @Test
    fun test4() = runTest {
        flow {
            emit(1)
            delay(50)
            emit(2)
        }.collectLatest { value ->
            println("Collecting $value")
            delay(100) // Emulate work
            println("$value collected")
        }
    }
}