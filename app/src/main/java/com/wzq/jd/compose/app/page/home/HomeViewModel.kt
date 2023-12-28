package com.wzq.jd.compose.app.page.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.wzq.jd.compose.app.AppContainer
import com.wzq.jd.compose.app.data.ArticlePageSource
import com.wzq.jd.compose.app.data.DataRepos
import com.wzq.jd.compose.app.data.ProjectPageSource
import com.wzq.jd.compose.app.data.model.Categories
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/4
 *
 */
class HomeViewModel : ViewModel() {

    val categories = mutableStateListOf<Categories>()

    private val categoriesDao = AppContainer.database.categoriesDao()

    val articleList = Pager(PagingConfig(20), 0) {
        ArticlePageSource()
    }.flow.cachedIn(viewModelScope)

    val projectList = Pager(PagingConfig(20), 0) {
        ProjectPageSource()
    }.flow.cachedIn(viewModelScope)

    init {
        getCategories()
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