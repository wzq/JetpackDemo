package com.wzq.jetpack

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import java.lang.reflect.Proxy
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
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

    fun threadLog() {
        println(Thread.currentThread().name)
    }

    @Test
    fun test01() {
        runBlocking {
            threadLog()
            println(1111111111)
            delay(1000)
//            val s = async(Dispatchers.IO) {
//                threadLog()
//                println(2323232)
//                println(2323232)
//
//                println(2323232)
//
//                println(2323232)
//
//
//            }

            launch {
                threadLog()
                println(2222222)
                delay(1000)
            }
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
        threadLog()
        println("Scope is completed")
    }

    @Test
    fun test02() {
        runBlocking {
            println(3333)
            delay(1000)
            println(1111)
        }
        println(2222)
    }

    @Test
    fun test03(){
        val calendar = Calendar.getInstance()
        val a = calendar.get(Calendar.YEAR)
        val b = calendar.get(Calendar.MONTH)
        println("$a -- $b")
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        println(pattern.format(LocalDate.now()))
        val s = LocalDate.parse("2020-07-08", pattern)
        println(s.monthValue)
    }

}
