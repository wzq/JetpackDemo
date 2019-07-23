package com.wzq.jetpack.data

import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
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

}