package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.ArticleResult
import com.wzq.jetpack.model.Banner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeRepo : BaseRepo() {

    fun getBanners(): LiveData<List<Banner>>{

        val data: MutableLiveData<List<Banner>> = MutableLiveData()

        Linker.api.getBanners().enqueue(resultFactory {
            data.value = it?.data
        })

        return data
    }

    fun getArticles(pageNum: Int): LiveData<List<Article>> {
        val data: MutableLiveData<List<Article>> = MutableLiveData()

        Linker.api.getArticles(pageNum).enqueue(resultFactory {
            val temp = it?.data?.datas
            data.value = temp
        })

        return data
    }

    fun getLastProjects(pageNum: Int): LiveData<List<Article>> {

        val data: MutableLiveData<List<Article>> = MutableLiveData()
        Linker.api.getLastProjects(pageNum).enqueue(resultFactory {
            val temp = it?.data?.datas
            data.value = temp

        })
        return data

    }

    fun getCategoryArticles(pageNum: Int, cid: Int): LiveData<List<Article>> {
        val data: MutableLiveData<List<Article>> = MutableLiveData()

        Linker.api.getCategoryArticles(pageNum, cid).enqueue(resultFactory {
            val temp = it?.data?.datas
            data.value = temp
        })
        return data
    }


    private fun <T> resultFactory(action: (T?) -> Unit): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                action(response.body())
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
            }

        }
    }
}