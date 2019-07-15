package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.model.ArticleResult


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo): ViewModel() {

    fun getArticles(pageNum: Int): LiveData<ArticleResult>{
        return repo.getArticles(pageNum)
    }
}