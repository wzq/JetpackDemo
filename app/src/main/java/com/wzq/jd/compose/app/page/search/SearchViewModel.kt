package com.wzq.jd.compose.app.page.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jd.compose.app.data.DataRepos
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.HotWords
import com.wzq.jd.compose.app.page.PageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * create by wzq on 2023/12/5
 *
 */
class SearchViewModel : ViewModel() {

    private val _keywords = mutableStateOf("")
    val keywords: State<String> = _keywords

    val hotWords = mutableStateListOf<HotWords>()

    val pageState = mutableStateOf<PageState<List<ArticleItem>>>(PageState.None)

    init {
        getHotWords()
    }

    private fun getHotWords() {
        viewModelScope.launch {
            DataRepos.remoteRepo.getHotWords().onSuccess {
                hotWords.clear()
                hotWords.addAll(it.data)
            }
        }
    }

    fun setKeyWords(key: String) {
        _keywords.value = key
        if (key.isEmpty()) {
            pageState.value = PageState.None
        }
    }

    fun searchResult(key: String) {
        setKeyWords(key)
        if (key.isEmpty()) {
            return
        }
        pageState.value = PageState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000) // TODO:
            }
            DataRepos.remoteRepo.getSearchResult(key)
                .onSuccess {
                    pageState.value = PageState.Success(it.data.listData)
                }.onFailure {
                    pageState.value = PageState.Failure(it)
                }
        }
    }
}