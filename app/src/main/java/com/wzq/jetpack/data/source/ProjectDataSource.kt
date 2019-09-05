package com.wzq.jetpack.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.data.remote.NetworkState
import com.wzq.jetpack.model.result.ArticleResult
import com.wzq.jetpack.util.NETWORK_IO
import retrofit2.Call
import retrofit2.Response


/**
 * Created by wzq on 2019-07-22
 * work in io thread
 */
class ProjectDataSource : PageKeyedDataSource<Int, Article>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var retry: (() -> Any)? = null


    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            NETWORK_IO.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {

        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        val num = 0
        Linker.api.getLastProjects(num).enqueue(object : retrofit2.Callback<ArticleResult>{
            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error(t.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            }

            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
                retry = null
                callback.onResult(response.body()?.data?.datas ?: emptyList(), null, num+1)

            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        networkState.postValue(NetworkState.LOADING)
        val num = params.key
        Linker.api.getLastProjects(num).enqueue(object : retrofit2.Callback<ArticleResult>{
            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                retry = {
                    loadAfter(params, callback)
                }
                val error = NetworkState.error(t.message ?: "unknown error")
                networkState.postValue(error)
            }

            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                networkState.postValue(NetworkState.LOADED)
                retry = null
                callback.onResult(response.body()?.data?.datas ?: emptyList(), num+1)

            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {}
}

class ProjectDataSourceFactory: DataSource.Factory<Int, Article>() {

    //监听data source
    val sourceLiveData = MutableLiveData<ProjectDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = ProjectDataSource()
        sourceLiveData.postValue(source)
        return source
    }


}