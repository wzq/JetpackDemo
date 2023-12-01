package com.wzq.jd.compose.app.data

import kotlinx.serialization.SerialName
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

@Serializable
data class NetResultList<T>(
    val data: ResultList<T>,
    val errorCode: Int,
    val errorMsg: String
)

@Serializable
data class ResultList<T>(
    val curPage: Int,
    @SerialName("datas")
    val listData: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

