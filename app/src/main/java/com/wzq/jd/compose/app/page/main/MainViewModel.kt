package com.wzq.jd.compose.app.page.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.ArticleItem
import com.wzq.jd.compose.app.data.RemoteDataRepo
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/4
 *
 */
class MainViewModel: ViewModel() {

    val homeList = mutableStateListOf<ArticleItem>()
    val projectList = mutableStateListOf<ArticleItem>()

    init {
        println("MainViewModel create")
        getHomeArticleList()
        getProjectList()
    }

    private fun getHomeArticleList() {
        viewModelScope.launch {
            RemoteDataRepo.getHomeArticleList().onSuccess {
                homeList.clear()
                homeList.addAll(it.data.listData)
            }
        }
    }

    private fun getProjectList(){
        viewModelScope.launch {
            RemoteDataRepo.getProjectList().onSuccess {
                projectList.clear()
                projectList.addAll(it.data.listData)
            }
        }
    }
}