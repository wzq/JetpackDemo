package com.wzq.jetpack

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

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
        repeat(3) {
            print(it)
        }
    }
}
