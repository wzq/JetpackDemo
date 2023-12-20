package com.wzq.jd.compose.app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

/**
 * create by wzq on 2023/12/19
 *
 */
class CustomHttpClient {

    private val client by lazy {
        createClient()
    }

    suspend fun <T> doRequest(block: suspend HttpClient.() -> T) =
        runCatching { client.block() }.onFailure(defaultErrorHandler)


    private fun createClient() = HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
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

    private val defaultErrorHandler = fun(exception: Throwable) {
        exception.printStackTrace()
    }

}