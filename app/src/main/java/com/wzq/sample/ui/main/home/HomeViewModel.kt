@file:OptIn(ExperimentalPagingApi::class)

package com.wzq.sample.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.wzq.sample.data.MainRepo
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.model.Banner
import com.wzq.sample.data.paging.ArticleRemoteMediator
import com.wzq.sample.util.PAGE_SIZE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

/**
 * create by wzq on 2021/4/6
 *
 */
class HomeViewModel : ViewModel() {
    private val repo = MainRepo()

    val banners = MutableStateFlow<List<Banner>>(emptyList())


    val articleList = Pager(
        PagingConfig(PAGE_SIZE), remoteMediator = ArticleRemoteMediator(
            label = "home_data", db = AppDatabase.getInstance()
        )
    ) {
        AppDatabase.getInstance().articleDao().getPagingArticles()
    }.flow.cachedIn(viewModelScope)

    fun banner() = viewModelScope.launch {
        banners.value = repo.getBanner().getOrNull()?.data ?: emptyList()
    }

}