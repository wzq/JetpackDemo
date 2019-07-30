package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.CategoryRepo


/**
 * Created by wzq on 2019-07-12
 *
 */
class CategoryViewModel internal constructor(private val repo: CategoryRepo): ViewModel() {

//    val pc = PagedList.Config.Builder()
//            .setPageSize(20)
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(20)
//            .build()
//    val categoryList = CategoryDataSourceFactory(repo).toLiveData(
//        config = pc, initialLoadKey = 0, fetchExecutor = NETWORK_IO)

    val categoryList = repo.getCategory()
}