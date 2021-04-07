package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.data.source.HomePagerSource
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

    val articleList = Pager(PagingConfig(20), 0) {
        HomePagerSource()
    }.liveData

    val looper = flow {
        var flag = 0
        while (true) {
//            threadLog("flow run")
            delay(5000)
            emit(++flag)
        }
    }.flowOn(Dispatchers.Default).asLiveData()
}
