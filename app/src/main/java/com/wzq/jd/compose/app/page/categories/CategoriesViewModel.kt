package com.wzq.jd.compose.app.page.categories

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.NetworkUtil
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/11
 *
 */
class CategoriesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val initPosition = savedStateHandle["position"] ?: 0
    val categories: Categories? = savedStateHandle["data"]

    val pagerData = mutableStateMapOf<Int, List<ArticleItem>>()

    init {
        getItemList(initPosition)
    }

    fun getItemList(index: Int) {
        viewModelScope.launch {
            if (pagerData.containsKey(index)) {
                return@launch
            }
            val cid = categories?.children?.get(index)?.id
            NetworkUtil.remoteRepo.getArticleList(cid = cid).onSuccess { result ->
                pagerData[index] = result.data.listData
            }
        }
    }

}