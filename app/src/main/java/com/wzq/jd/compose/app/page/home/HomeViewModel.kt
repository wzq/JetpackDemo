package com.wzq.jd.compose.app.page.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.DataRepos
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.page.PageState
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/4
 *
 */
class HomeViewModel : ViewModel() {

    val indexState = mutableStateOf<PageState<List<ArticleItem>>>(PageState.Loading)

    val projectState = mutableStateOf<PageState<List<ArticleItem>>>(PageState.Loading)
    val projectList = mutableStateListOf<ArticleItem>()
    val categories = mutableStateListOf<Categories>()

    init {
        getArticleList()
        getProjectList()
        getCategories()
    }

    private fun getArticleList(pageNum: Int = 0) {
        viewModelScope.launch {
            DataRepos.remoteRepo.getArticleList(pageNum)
                .onSuccess { indexState.value = PageState.Success(it.data.listData) }
                .onFailure { indexState.value = PageState.Failure(it) }
        }
    }

    private fun getProjectList() {
        viewModelScope.launch {
            DataRepos.remoteRepo.getProjectList().onSuccess {
                projectState.value = PageState.Success(it.data.listData)
            }.onFailure { PageState.Failure(it) }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            DataRepos.remoteRepo.getKnowledgeCategories().onSuccess {
                categories.clear()
                categories.addAll(it.data)
            }
        }
    }

}