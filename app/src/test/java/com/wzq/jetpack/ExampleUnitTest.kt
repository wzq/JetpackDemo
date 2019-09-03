package com.wzq.jetpack

import com.wzq.jetpack.util.IOScope
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() = runBlocking{
        launch {
            println(2)
        }
        println(1)
    }


    suspend fun apple(){
        delay(1000)
    }


    @Test
    fun test01() {
        IOScope().launch {
            println("${Thread.currentThread().name} io scope")
            throw Exception("test exception")
        }
    }
}
