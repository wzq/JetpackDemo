package com.wzq.jetpack.model.result

/**
 * create by wzq on 2020/8/12
 *
 */
class NetResult<T>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    val data: T? = null
)