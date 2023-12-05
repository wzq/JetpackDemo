package com.wzq.jd.compose.app.page.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.NetworkUtil
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.SearchHotWords
import kotlinx.coroutines.launch

/**
 * create by wzq on 2023/12/5
 *
 */
class SearchViewModel : ViewModel() {

    private val _keywords = mutableStateOf("")
    val keywords: State<String> = _keywords

    val hotWords = mutableStateListOf<SearchHotWords>()
    val result = mutableStateListOf<ArticleItem>()

    init {
        getHotWords()
    }

    private fun getHotWords() {
        viewModelScope.launch {
            NetworkUtil.remoteRepo.getHotWords().onSuccess {
                hotWords.clear()
                hotWords.addAll(it.data)
            }
        }
    }

    fun setKeyWords(key: String) {
        _keywords.value = key
        if (key.isEmpty()) {
            result.clear()
        }
    }

    fun searchResult(key: String) {
        setKeyWords(key)
        viewModelScope.launch {
            NetworkUtil.remoteRepo.getSearchResult(key)
                .onSuccess {
                    result.clear()
                    result.addAll(it.data.listData)
                }
        }
    }
}