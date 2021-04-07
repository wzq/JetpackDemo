package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.data.remote.RespCallback
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Category

/**
 * Created by wzq on 2019-07-23
 *
 */
class CategoryRepo : BaseRepo() {

    fun getCategoryDefault(pageNum: Int, callback: (it: List<Article>) -> Unit) {
        Linker.api.getCategoryArticles(pageNum, 60).enqueue(
            RespCallback {
                val temp = it.getOrNull()?.data?.datas
                callback(temp!!)
            }
        )
    }

    fun getCategoryArticle(cid: Int): LiveData<List<Article>> {
        val data = MutableLiveData<List<Article>>()
        Linker.api.getCategoryArticles(0, cid).enqueue(
            RespCallback {
                data.value = it.getOrNull()?.data?.datas
            }
        )
        return data
    }

    fun getCategory(): LiveData<List<Category>> {
        val data = MutableLiveData<List<Category>>()
        Linker.api.getCategory().enqueue(
            RespCallback {
                data.value = it.getOrNull()?.data
            }
        )
        return data
    }
}
