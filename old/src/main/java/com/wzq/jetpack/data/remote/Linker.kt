package com.wzq.jetpack.data.remote

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.wzq.jetpack.BaseInitializer
import com.wzq.jetpack.data.remote.api.Api
import com.wzq.jetpack.data.remote.api.GankApi
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Linker {

    private const val BASE_URL = "https://www.wanandroid.com"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().cookieJar(
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(BaseInitializer.app)
        )
    ).addInterceptor(logger).build()

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL.toHttpUrl())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Api by lazy { retrofit.create() }

    val gankApi: GankApi by lazy { retrofit.create() }
}
