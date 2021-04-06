package com.wzq.app2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wzq.app2.data.model.ArticleList
import com.wzq.app2.data.model.DataResult
import com.wzq.app2.data.remote.Linker
import kotlinx.coroutines.launch

/**
 * create by wzq on 2021/4/6
 *
 */
class MainViewModel: ViewModel() {

    private val _homeData = MutableLiveData<DataResult<ArticleList>>()
    val homeData: LiveData<DataResult<ArticleList>> = _homeData

    init {
        viewModelScope.launch {
            fetchHomeData(1)
        }
    }

    suspend fun fetchHomeData(pageNum: Int) {
        _homeData.value = DataResult.catch {
            Linker.mainApi.getArticles(pageNum)
        }
    }
}