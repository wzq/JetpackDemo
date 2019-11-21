package com.wzq.jetpack.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo) : ViewModel() {


    val banners = repo.getBanners()
//
//    private val listing = repo.getArticles()
//
//    val articles = listing.pagedList
//
//    val doRefresh = listing.refresh
//    val networkState = listing.networkState
//    val refreshState = listing.refreshState

    val pageNum = MutableLiveData(0)

    val articleList = pageNum.switchMap {
        repo.getArticles(it)
    }

    @ExperimentalCoroutinesApi
    val looper = flow {
        threadLog("flow run")
        var flag = 0
        while (true) {
            delay(5000)
            if (flag >= 2) flag = 0 else flag++
            emit(flag)
        }
    }.flowOn(Dispatchers.IO).asLiveData()


    @MainThread
    fun refresh() {
        pageNum.value = 0
    }

    @MainThread
    fun nextPage() {
        pageNum.value = pageNum.value?.plus(1)
    }

}