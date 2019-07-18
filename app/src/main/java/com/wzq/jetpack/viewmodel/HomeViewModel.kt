package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.model.Article


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo): ViewModel() {


    val banners = repo.getBanners()

    fun getArticles(pageNum: Int): LiveData<List<Article>>{
        return (repo.getArticles(pageNum))
    }

    fun getLastProjects(pageNum: Int): LiveData<List<Article>>{
        return repo.getLastProjects(pageNum)
    }

    fun getCategoryArticles(pageNum: Int,cid: Int): LiveData<List<Article>>{
        return repo.getCategoryArticles(pageNum, cid)
    }
}