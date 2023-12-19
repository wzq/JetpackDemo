package com.wzq.jd.compose.app.data.model

import kotlinx.serialization.Serializable

/**
 * create by wzq on 2023/11/27
 *
 */
@Serializable
data class NetResult<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)

