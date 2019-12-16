package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.Todo

data class TodoResult(
    val data: TodoData
): BaseResult()

data class TodoData(
    val curPage: Int,
    var datas: MutableList<Todo>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)