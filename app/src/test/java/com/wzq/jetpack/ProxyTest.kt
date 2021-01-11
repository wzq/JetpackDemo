package com.wzq.jetpack

import org.junit.Test
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * create by wzq on 2021/1/4
 *
 */


interface A {
    fun say()
}

class ProxyTest(val proxy: A) : A by proxy {

    fun main() {
        say()
    }
}

val aObj = object : A {
    override fun say() {
        println("aObj say")
    }
}

class Test {
    @Test
    fun go() {

        ProxyTest(aObj).main()
    }

    @Test
    fun go2() {
        val clazz = A::class.java
        val proxy = Proxy.newProxyInstance(
            clazz.classLoader,
            arrayOf(clazz)
        ) { proxy, method, args ->
            method.invoke(aObj, *args ?: emptyArray())
        } as A

        proxy.say()
    }
}
