package com.wzq.sample.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.data.MainRepo
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.paging.ArticleRemoteMediator
import com.wzq.sample.util.PAGE_SIZE

/**
 * create by wzq on 2021/4/6
 *
 */
class HomeViewModel : ViewModel() {
    private val repo = MainRepo()

    private val database = AppDatabase.getInstance()

    @OptIn(ExperimentalPagingApi::class)
    val mediator = ArticleRemoteMediator(
        label = "home_data",
        db = database
    ) {
        println(it)
        repo.getHomeArticles(it).getOrNull()?.data.also {
            println(it)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    val articleList = Pager(
        PagingConfig(PAGE_SIZE),
        remoteMediator = mediator
    ) {
        database.articleDao().getPagingArticles()
    }

}