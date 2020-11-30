package com.wzq.jetpack.data.remote

/**
 * create by wzq on 2020/9/3
 *
 */
class Snapshot<T>(
    val data: T? = null,
    val error: RespError? = null
) {
    fun hasData() = data != null

    fun hasError() = error != null
}

class RespError

