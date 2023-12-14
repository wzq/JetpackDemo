package com.wzq.jd.compose.app.page.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.NetworkUtil
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/4
 *
 */
class HomeViewModel : ViewModel() {

    val homeList = mutableStateListOf<ArticleItem>()
    val projectList = mutableStateListOf<ArticleItem>()
    val categories = mutableStateListOf<Categories>()

    init {
        getArticleList()
        getProjectList()
        getCategories()
    }

    private fun getArticleList(pageNum: Int = 0, cid: String? = null) {
        viewModelScope.launch {
            NetworkUtil.remoteRepo.getArticleList(pageNum, cid).onSuccess {
                homeList.clear()
                homeList.addAll(it.data.listData)
            }
        }
    }

    private fun getProjectList() {
        viewModelScope.launch {
            NetworkUtil.remoteRepo.getProjectList().onSuccess {
                projectList.clear()
                projectList.addAll(it.data.listData)
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            NetworkUtil.remoteRepo.getKnowledgeCategories().onSuccess {
                categories.clear()
                categories.addAll(it.data)
            }
        }
    }

}