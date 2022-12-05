package com.wzq.sample.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.sample.data.model.ArticleList
import com.wzq.sample.data.remote.Linker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * create by wzq on 2022/11/18
 *
 */
class SearchViewModel(private val state: SavedStateHandle) : ViewModel() {

    private val _result = MutableStateFlow<ArticleList?>(null)
    val result: StateFlow<ArticleList?> = _result

    fun search(kw: String) {
        viewModelScope.launch {
            runCatching {
                Linker.mainApi.queryBySearchKey(0, kw).data
            }.onSuccess {
                _result.update { it }
            }
        }
    }
}