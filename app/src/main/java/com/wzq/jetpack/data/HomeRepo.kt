package com.wzq.jetpack.data

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.wzq.jetpack.data.local.AppDatabase
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.data.source.HomeDataSourceFactory
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner
import com.wzq.jetpack.model.Listing
import com.wzq.jetpack.util.NETWORK_IO
import com.wzq.jetpack.util.thread.IOScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeRepo : BaseRepo() {

    fun getBanners(): LiveData<List<Banner>> = liveData {
        val result = Linker.api.getBanners().data
        emit(result)
        withContext(Dispatchers.IO) {
            val s = AppDatabase.getInstance().bannerDao().insert(result)
            Timber.d(s?.toString())
        }
    }

    suspend fun getArticles(pageNum: Int = 0) =
        Linker.api.getArticles(pageNum)?.data?.datas ?: emptyList<Article>()

}