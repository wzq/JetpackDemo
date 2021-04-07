package com.wzq.jetpack.model.result

data class ListResult<T> (
    val data: ListData<T>,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)

data class ListData<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
