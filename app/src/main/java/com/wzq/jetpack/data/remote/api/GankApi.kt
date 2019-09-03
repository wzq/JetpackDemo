package com.wzq.jetpack.data.remote.api

import com.wzq.jetpack.model.result.GankResult
import retrofit2.http.GET

interface GankApi {

    @GET("http://gank.io/api/today")
    suspend fun getDaliyInfo(): GankResult


    @GET("banner/json")
    suspend fun errorTest(): GankResult
}