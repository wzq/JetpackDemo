package com.wzq.sample.ui.main.project

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.data.MainRepo
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.paging.ArticleRemoteMediator
import com.wzq.sample.util.PAGE_SIZE

/**
 * create by wzq on 2021/4/22
 *
 */
class ProjectViewModel: ViewModel() {
    private val database = AppDatabase.getInstance()

    private val repo = MainRepo()

    @ExperimentalPagingApi
    private val mediator = ArticleRemoteMediator(
        label = "home_data",
        db = database
    ) {
        repo.getProjects(it).getOrNull()?.data
    }

    @ExperimentalPagingApi
    val articleList = Pager(
        PagingConfig(PAGE_SIZE),
        remoteMediator = mediator
    ) {
        database.articleDao().getPagingArticles()
    }

}