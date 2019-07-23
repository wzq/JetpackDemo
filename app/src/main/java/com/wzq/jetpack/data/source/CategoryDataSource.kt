package com.wzq.jetpack.data.source

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wzq.jetpack.data.CategoryRepo
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.model.Article


/**
 * Created by wzq on 2019-07-18
 *
 */
class CategoryDataSource(val repo: CategoryRepo) : PageKeyedDataSource<Int, Article>(){
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        repo.getCategoryDefault(0){
            callback.onResult(it, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val pageNum = params.key
        repo.getCategoryDefault(pageNum){
            callback.onResult(it, pageNum + 1 )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

}

class CategoryDataSourceFactory(private val repo: CategoryRepo): DataSource.Factory<Int, Article>(){
    override fun create(): DataSource<Int, Article> {
        return CategoryDataSource(repo)
    }

}