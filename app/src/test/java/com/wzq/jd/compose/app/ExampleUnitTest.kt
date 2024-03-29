package com.wzq.jd.compose.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
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
    fun test() = runBlocking {

        val supervisorJob = SupervisorJob()

        val parentJob = launch(supervisorJob) {
            val childJob1 = launch {
                // 子协程1的操作
                println(1)
            }

            val childJob2 = launch {
                // 子协程2的操作，可能会失败
                throw Exception("Child job 2 failed")
            }

            val job3 = launch {
                println(3)
            }
        }

        parentJob.join()

        Unit
    }


    @Test
    fun goodTest() = runBlocking {
        val job = SupervisorJob()

        launch(job) {
            println(1)
        }

        launch(job) {
            throw Exception("job2")
        }

        launch(job) {
            println(3)
        }

        Unit
    }

    @Test
    fun testAsync() = runBlocking {
        println(1)
        withContext(Dispatchers.IO) {
            delay(5000)
        }
        println(3)
    }
}