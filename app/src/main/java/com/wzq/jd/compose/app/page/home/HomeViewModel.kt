package com.wzq.jd.compose.app.page.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.DataRepos
import com.wzq.jd.compose.app.data.local.AppDatabase
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

    val categories = mutableStateListOf<Categories>()

    private val categoriesDao = AppDatabase.instance.categoriesDao()

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
            val local = categoriesDao.getCategoriesAll()
            if (local.isNotEmpty()) {
                val listData = mutableListOf<Categories>()
                val group = local.groupBy { it.parentChapterId }
                group[0]?.forEach { item ->
                    item.children = group[item.id] ?: emptyList()
                    listData.add(item)
                }
                categories.clear()
                categories.addAll(listData)
            } else {
                DataRepos.remoteRepo.getKnowledgeCategories().onSuccess {
                    categories.clear()
                    categories.addAll(it.data)
                    it.data.let { list ->
                        val s = mutableListOf<Categories>()
                        list.forEach {
                            s.add(it)
                            s.addAll(it.children)
                        }
                        categoriesDao.insert(s)
                    }
                }
            }
        }
    }

}