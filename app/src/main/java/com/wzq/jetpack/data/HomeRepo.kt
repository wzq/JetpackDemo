package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.ArticleResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeRepo: BaseRepo() {

    fun getArticles(pageNum: Int): LiveData<ArticleResult>{
        val data: MutableLiveData<ArticleResult> = MutableLiveData()

        Linker.api.getArticles(pageNum).enqueue(resultFactory {
            data.value = it
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