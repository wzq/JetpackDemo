package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.HomeRepo


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo): ViewModel() {


    val banners = repo.getBanners()

    fun getArticles(pageNum: Int) = repo.getArticles(pageNum)
}