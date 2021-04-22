package com.wzq.sample.ui.main.project

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.paging.ArticleRemoteMediator
import com.wzq.sample.data.remote.Linker
import com.wzq.sample.util.PAGE_SIZE

/**
 * create by wzq on 2021/4/22
 *
 */
class ProjectViewModel {
    private val database = AppDatabase.getInstance()

    @ExperimentalPagingApi
    private val mediator = ArticleRemoteMediator(
        label = "home_data",
        db = database
    ) {
        Linker.mainApi.getProjects(it).getOrThrow()
    }

    @ExperimentalPagingApi
    val articleList = Pager(
        PagingConfig(PAGE_SIZE),
        remoteMediator = mediator
    ) {
        database.articleDao().getPagingArticles()
    }

}