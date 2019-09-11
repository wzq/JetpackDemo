package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.Article

data class TodoResult(
    val data: TodoData
): BaseResult()

data class TodoData(
    val curPage: Int,
    var datas: MutableList<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)