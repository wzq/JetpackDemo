package com.wzq.jetpack.data.remote

import com.wzq.jetpack.data.remote.api.Api
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


object Linker {

//    private const val BASE_URL = "http://132.232.98.141/"
    private const val BASE_URL = "https://www.wanandroid.com"


    private val logger = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { Timber.d(it) }
    ).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(logger).build()

    private val url = HttpUrl.parse(BASE_URL)!!
    private val retrofit = Retrofit.Builder().baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            ///.addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

    val api: Api by lazy { retrofit.create(Api::class.java) }

}