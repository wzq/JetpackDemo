package com.wzq.jetpack.data.remote.api

import com.wzq.jetpack.model.result.GankResult
import retrofit2.http.GET

interface GankApi {

    @GET("http://gank.io/api/today")
    suspend fun getDaliyInfo(): GankResult

    @GET("http://gank.io/adsdxc")
    suspend fun errorTest(): GankResult
}
