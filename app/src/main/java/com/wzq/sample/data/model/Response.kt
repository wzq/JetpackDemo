package com.wzq.sample.data.model

/**
 * create by wzq on 2021/4/9
 *
 */
data class Response<T>(
    val data: T,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)