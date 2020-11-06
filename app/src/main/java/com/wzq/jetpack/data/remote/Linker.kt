package com.wzq.jetpack.data.remote

import com.wzq.jetpack.data.remote.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import okhttp3.HttpUrl.Companion.toHttpUrl

import com.wzq.jetpack.BaseInitializer
import com.wzq.jetpack.data.remote.api.GankApi
import retrofit2.create


object Linker {

//    private const val BASE_URL = "http://132.232.98.141/"
    private const val BASE_URL = "https://www.wanandroid.com"


    private val logger = HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger.DEFAULT
    ).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().cookieJar(
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(BaseInitializer.app)
        )).addInterceptor(logger).build()


    private val url = BASE_URL.toHttpUrl()
    private val retrofit = Retrofit.Builder().baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: Api by lazy { retrofit.create<Api>() }


    val gankApi : GankApi by lazy { retrofit.create<GankApi>()}

}