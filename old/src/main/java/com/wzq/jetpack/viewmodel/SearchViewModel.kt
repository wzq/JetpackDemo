package com.wzq.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.jetpack.data.SearchRepo
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.Prefs
import kotlinx.coroutines.launch

class SearchViewModel internal constructor(private val repo: SearchRepo) : ViewModel() {

    val hotWords = repo.getHotWords()

    val searchResult = MutableLiveData<List<Article>>()

    fun searchAny(query: String?, pageNum: Int = 0) {
        if (query.isNullOrBlank()) return
        Prefs.appendString(Prefs.SEARCH_HISTORY, "$query|", false)
        viewModelScope.launch {
            val s = repo.searchAny(query, pageNum)
            searchResult.postValue(s.data.datas)
        }
    }

    val history = arrayListOf<String>().apply {
        val s = Prefs.get(Prefs.SEARCH_HISTORY, "")
        if (s.isNotBlank()) {
            addAll(s.split("|").filter { it.isNotBlank() })
        }
        reverse()
    }
}
