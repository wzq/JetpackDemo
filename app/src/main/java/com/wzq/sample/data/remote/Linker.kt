package com.wzq.sample.data.remote

import com.wzq.sample.App
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File

object Linker {

    private const val BASE_URL = "https://www.wanandroid.com"
    private const val CACHE_FILE_NAME = "ok_http_cache"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL.toHttpUrl()).client(buildHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun buildHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val cache = Cache(
            File(App.context.cacheDir, CACHE_FILE_NAME), 50L * 1024L * 1024L
        )

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .cache(cache)
//            .eventListener(HttpEventListener())
            .build()
    }

    val mainApi: MainApi by lazy { retrofit.create() }

}
