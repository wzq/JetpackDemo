package com.wzq.jetpack.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * create by wzq on 2021/1/4
 *
 */
class RespCallback<T>(val onResp: OnResp<T>) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val body = response.body()
        val s = if (body == null) {
            Snapshot.failure<T>(Exception("Response Body Empty !!"))
        } else {
            Snapshot.success(body)
        }
        onResp(s)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val s = Snapshot.failure<T>(t)
        onResp(s)
    }
}

typealias OnResp<T> = (snapshot: Snapshot<T>) -> Unit
