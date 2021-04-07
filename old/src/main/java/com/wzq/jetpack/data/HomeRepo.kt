package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wzq.jetpack.data.local.AppDatabase
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner
import kotlinx.coroutines.Dispatchers
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
