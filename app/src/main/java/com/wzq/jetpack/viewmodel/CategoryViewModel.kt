package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.CategoryRepo
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Category


/**
 * Created by wzq on 2019-07-12
 *
 */
class CategoryViewModel internal constructor(private val repo: CategoryRepo): ViewModel() {

//    val pc = PagedList.Config.Builder()
//            .setPageSize(20)
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(20)
//            .build()
//    val categoryList = CategoryDataSourceFactory(repo).toLiveData(
//        config = pc, initialLoadKey = 0, fetchExecutor = NETWORK_IO)

    fun getCategoryList(): LiveData<List<Category>> {
        return repo.getCategory()
    }


    fun getArticleList(cid: Int): LiveData<List<Article>> {
        return repo.getCategoryArticle(cid)
    }

}