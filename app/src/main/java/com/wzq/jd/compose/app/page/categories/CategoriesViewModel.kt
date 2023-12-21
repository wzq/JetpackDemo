package com.wzq.jd.compose.app.page.categories

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.App
import com.wzq.jd.compose.app.data.DataRepos
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/11
 *
 */
class CategoriesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val initPosition = savedStateHandle["position"] ?: 0
    val categories: Categories? = savedStateHandle["data"]

    val pagerData = mutableStateMapOf<Int, List<ArticleItem>>()

    fun getItemList(index: Int, batch: Int = 0) {
        viewModelScope.launch {
            if (pagerData.containsKey(index)) {
                return@launch
            }
            val cid = categories?.children?.get(index)?.id ?: 0
            println(cid)
            pagerData[index] = App.db.articleDao().getArticlesByCid(cid).also {
                println(it)
            }
//            val all = (App.db.articleDao().getArticlesAll())
//            println(all.size)
            launch {
                delay(5000)
                DataRepos.remoteRepo.getArticleList(cid = cid).onSuccess { result ->
                    println(result.data.listData)
                    pagerData[index] = result.data.listData
                }
            }
            println(11111)
        }
    }

}