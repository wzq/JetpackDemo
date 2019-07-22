package com.wzq.jetpack.viewmodel

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article


/**
 * Created by wzq on 2019-07-18
 *
 */
class CategoryDS(val repo: HomeRepo) : PageKeyedDataSource<Int, Article>(){
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        val s= params.requestedLoadSize
        println("~~~~$s")
        println(repo)
//        repo.getCategoryDefault(0){
//            callback.onResult(it, null, 1)
//        }
        val d = Linker.api.getCategoryArticles(0, 60).execute()
        val data = d.body()?.data?.datas ?: emptyList<Article>()
        callback.onResult(data, null, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val kkk = params.key
        val ooo = params.requestedLoadSize
        println("$kkk --- $ooo")
        repo.getCategoryDefault(kkk){
            callback.onResult(it, kkk+1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

}

class DSFactory(val repo: HomeRepo): DataSource.Factory<Int, Article>(){
    override fun create(): DataSource<Int, Article> {
        return CategoryDS(repo)
    }

}