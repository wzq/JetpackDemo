package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Category
import com.wzq.jetpack.util.resultFactory


/**
 * Created by wzq on 2019-07-23
 *
 */
class CategoryRepo: BaseRepo(){


    fun getCategoryDefault(pageNum: Int, callback: (it: List<Article>)->Unit) {
        Linker.api.getCategoryArticles(pageNum, 60).enqueue(resultFactory {
            val temp = it?.data?.datas
            callback(temp!!)
        })
    }

    fun getCategory(): LiveData<List<Category>> {
        val data = MutableLiveData<List<Category>>()
        Linker.api.getCategory().enqueue(resultFactory {
            data.value = it?.data ?: emptyList()
        })
        return data
    }

}