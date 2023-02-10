package com.wzq.sample.data.remote

import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Linker {

    private const val BASE_URL = "https://www.wanandroid.com"

    private val retrofit by lazy {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

        Retrofit.Builder().baseUrl(BASE_URL.toHttpUrl()).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val mainApi: MainApi by lazy { retrofit.create() }

}
