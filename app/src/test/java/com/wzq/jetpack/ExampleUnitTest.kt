package com.wzq.jetpack

import android.util.SparseArray
import com.google.gson.Gson
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.wzq.jetpack.util.html
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val sa = SparseArray<String>()


        val a = 2
        println(a.inv().inv())

        println(a.or(4))

        println(a.and(3))

        println(a shl 2)
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
    fun test03() {
        val calendar = Calendar.getInstance()
        val a = calendar.get(Calendar.YEAR)
        val b = calendar.get(Calendar.MONTH)
        println("$a -- $b")
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        println(pattern.format(LocalDate.now()))
        val s = LocalDate.parse("2020-07-08", pattern)
        println(s.monthValue)
    }

    @Test
    fun test04(): Unit {
        val html = html {
            head {
                title { +"XML encoding with Kotlin" }
            }
            body {
                h1 { +"XML encoding with Kotlin" }
                p { +"this format can be used as an alternative markup to XML" }

                // 一个具有属性和文本内容的元素
                a(href = "http://kotlinlang.org") { +"Kotlin" }

                // 混合的内容
                p {
                    +"This is some"
                    b { +"mixed" }
                    +"text. For more see the"
                    a(href = "http://kotlinlang.org") { +"Kotlin" }
                    +"project"
                }
                p { +"some text" }
            }
        }
        println(html.toString())

    }

    @Test
    fun test05() {
        val flow = flow<Int> {

            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }

        runBlocking {
            val t1 = measureTimeMillis {
                flow.buffer().collect {
                    println("---t1")
                    delay(300)
                    println("t1 $it")
                }
            }

            val t2 = measureTimeMillis {
                flow.conflate().collect {
                    println("---t2")
                    delay(300)
                    check(it < 1) { ("check $it") }
                    println("t2 $it")
                }
            }

            println("$t1 --- $t2")

        }

    }


    @Test
    fun test06() {

        val oddNumbers = sequence {
            yield(1)
//            Thread.sleep(1000)
            yieldAll(listOf(3, 5))
         //   yieldAll(generateSequence(7) { it + 2 })
        }

        println(oddNumbers.onEach {
            println(it)
        })
    }


    @Test
    fun test07() {
        val json = """
                 {
            "code": 200,
            "msg": "提醒设置成功",
            "data": null
        }
        """.trimIndent()
        val s = Gson().fromJson(json, Resp::class.java)

        println(s.data)

        val s1 = Gson().fromJson(json, JsonObject::class.java)
        println(s1.toString())
        println(s1.get("data").isJsonPrimitive)

        println(s1.get("abc").isJsonNull)
    }

    data class Resp(
        val code: Int,
        val msg: String,
        val data: JsonNull?
    )

}
