package com.wzq.jetpack

import org.junit.Test

/**
 * create by wzq on 2021/1/18
 *
 */
class AnnotationTest {

    val target: BaseA = TestA()

    @Test
    fun tests() {
        val clazz = target.javaClass
        clazz.methods.forEach {
            println(it.name)
        }

        val m = clazz.getMethod("say", String::class.java)
        m.invoke(target, "abc")
    }
}

abstract class BaseA {
    open fun say(s: String) {
        println(111)
    }
}
class TestA: BaseA() {
    override fun say(s: String) {
        println(s)
    }

}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class TestAnnotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FuncAnnotation