package com.wzq.jetpack

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import java.lang.reflect.Proxy
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.TestClock
import kotlin.time.seconds

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
    fun test() = runBlocking {
        launch {
            println(2)
        }
        println(1)
    }


    suspend fun apple() {
        delay(1000)
    }

    @Test
    fun test01() {
        runBlocking {

            val s = async(Dispatchers.IO) {
                println(2323232)
                println(2323232)

                println(2323232)

                println(2323232)


            }

            launch {
                println(2222222)
            }
            println(1111111111)

//            val handler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
//                throwable.printStackTrace()
//
//            }
//            supervisorScope {
//                val child = launch(handler) {
//                    println("Child throws an exception")
//                    throw AssertionError()
//                }
//                println("Scope is completing")
//            }
        }
        println("Scope is completed")
    }

}
