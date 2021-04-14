package com.wzq.sample.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.util.PAGE_SIZE
import kotlinx.coroutines.flow.map

/**
 * create by wzq on 2021/4/6
 *
 */
class HomeViewModel : ViewModel() {

    private val database = AppDatabase.getInstance()

    @ExperimentalPagingApi
    val articleList = Pager(
        PagingConfig(PAGE_SIZE),
        remoteMediator = HomeRemoteMediator(database)
    ) {
        database.articleDao().getPagingArticles()
    }

}