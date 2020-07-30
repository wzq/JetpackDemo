package com.wzq.jetpack

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * create by wzq on 2020/7/30
 *
 */
class CoroutinesTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Test
    fun whenException(){
        val scope = CoroutineScope(SupervisorJob())

        scope.launch {
            thrown.expect(Exception::class.java)

            println(1)
        }

        scope.launch {
            println(2)

        }

    }
}