package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.Article
import kotlinx.coroutines.flow.flow

/**
 * create by wzq on 2020/8/12
 *
 */
class NetResult<T>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    val data: T? = null
) {
    fun toFlow() = flow {
        emit(this@NetResult)
    }
}

typealias TopArticles = NetResult<List<Article>>