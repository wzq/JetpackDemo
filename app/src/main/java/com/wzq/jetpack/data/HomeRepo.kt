package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.wzq.jetpack.data.local.AppDatabase
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner
import com.wzq.jetpack.util.resultFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeRepo : BaseRepo() {

    fun getBanners(): LiveData<List<Banner>> {
        val data: MutableLiveData<List<Banner>> = MutableLiveData()
        Linker.api.getBanners().enqueue(resultFactory {
            data.value = it?.data
            GlobalScope.launch {
                val s = AppDatabase.getInstance().bannerDao().insert(it?.data!!)
                Timber.d(s?.toString())
            }
        })

        return data
    }

    fun getArticles(pageNum: Int): LiveData<List<Article>> {
        val data: MutableLiveData<List<Article>> = MutableLiveData()

        Linker.api.getArticles(pageNum).enqueue(resultFactory {
            val temp = it?.data?.datas
            GlobalScope.launch {
                val s = AppDatabase.getInstance().articleDao().insert(temp!!)
                Timber.d(s?.toString())
            }
            data.value = temp
        })

        return data
    }


}