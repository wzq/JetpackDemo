package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.data.source.DSFactory
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.NETWORK_IO


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo): ViewModel() {


    val banners = repo.getBanners()

    fun getArticles(pageNum: Int): LiveData<List<Article>>{
        return (repo.getArticles(pageNum))
    }

    fun getCategoryArticles(pageNum: Int,cid: Int): LiveData<List<Article>>{
        return repo.getCategoryArticles(pageNum, cid)
    }

    val pc = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .build()
    val categoryList = DSFactory(repo).toLiveData(
        config = pc, initialLoadKey = 0, fetchExecutor = NETWORK_IO)
}