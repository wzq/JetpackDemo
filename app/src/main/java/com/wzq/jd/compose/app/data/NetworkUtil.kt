package com.wzq.jd.compose.app.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

/**
 * create by wzq on 2023/11/27
 *
 */
object NetworkUtil {

    object Url {
        const val HomeArticleList = "https://www.wanandroid.com/article/list/0/json"
    }

    suspend inline fun <reified T> requestGet(url: String): T {
        return client.get(url).body()
    }

    val client by lazy {
        HttpClient(OkHttp) {
            engine {
                config {
                    followRedirects(true)
                }
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })

            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}