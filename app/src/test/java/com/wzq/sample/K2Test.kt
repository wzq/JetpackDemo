package com.wzq.sample

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * create by wzq on 2023/8/9
 *
 */
class K2Test {

    @Test
    fun t1() = runTest {
        async {
            repeat(20) {
                delay(1000)
                println(it)
            }
        }
    }
}