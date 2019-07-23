package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.wzq.jetpack.data.CategoryRepo
import com.wzq.jetpack.data.source.CategoryDataSourceFactory
import com.wzq.jetpack.util.NETWORK_IO


/**
 * Created by wzq on 2019-07-12
 *
 */
class CategoryViewModel internal constructor(private val repo: CategoryRepo): ViewModel() {

    val pc = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .build()
    val categoryList = CategoryDataSourceFactory(repo).toLiveData(
        config = pc, initialLoadKey = 0, fetchExecutor = NETWORK_IO)
}