package com.wzq.jd.compose.app.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

/**
 * create by wzq on 2023/11/27
 *
 */
object NetworkUtil {
    private const val BASE_URL = "https://www.wanandroid.com/"

    private val client by lazy {
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

//            install(Logging) {
//                logger = Logger.ANDROID
//                level = LogLevel.ALL
//            }
        }
    }

    val remoteRepo by lazy { RemoteDataRepo(client, BASE_URL) }

}