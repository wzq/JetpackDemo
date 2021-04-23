package com.wzq.sample

import com.wzq.sample.data.remote.Linker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

/**
 * create by wzq on 2021/4/23
 *
 */
class NetworkTest {

    @Test
    fun test(): Unit = runBlocking {
        launch {
            withContext(Dispatchers.IO) {
                delay(5000)
                println(Thread.currentThread().name)
            }
            println(321)
            val s = Linker.mainApi.getProjects(1)
            println(s.getOrThrow())
        }

    }
}