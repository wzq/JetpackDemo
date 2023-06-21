package com.wzq.sample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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
}