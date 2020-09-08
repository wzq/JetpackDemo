package com.wzq.jetpack.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo) : ViewModel() {


    val banners = repo.getBanners()

    val pageNum = MutableLiveData(0)

    val articleList = pageNum.switchMap {
        liveData { emit(repo.getArticles(it)) }
    }

    val looper = flow {
        threadLog("flow run")
        var flag = 0
        while (true) {
            delay(5000)
            emit(++flag)
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