package com.wzq.jetpack.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.data.remote.NetworkState
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.NETWORK_IO
import com.wzq.jetpack.util.thread.IOScope
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.launch


/**
 * paging 在滚动嵌套时会加载所有数据
 * invalidate 刷新数据时导致闪烁
 */
@Deprecated("deprecated since paging 3.0")
class HomeDataSource : PageKeyedDataSource<Int, Article>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var retry: (() -> Any)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        val pageNum = 0
        IOScope {
            retry = { loadInitial(params, callback)}
            val error = NetworkState.error(it.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }.launch {
            retry = null
            val data = Linker.api.getArticles(pageNum)?.data?.datas ?: emptyList<Article>()
            callback.onResult(data,  null, pageNum + 1)
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        networkState.postValue(NetworkState.LOADING)
        val pageNum = params.key
        IOScope {
            retry = { loadAfter(params, callback)}
            val error = NetworkState.error(it.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }.launch {
            retry = null
            val data = Linker.api.getArticles(pageNum)?.data?.datas ?: emptyList<Article>()
            callback.onResult(data, pageNum + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {}

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            NETWORK_IO.execute {
                it.invoke()
            }
        }
    }

}

class HomeDataSourceFactory: DataSource.Factory<Int, Article>() {

    //监听data source
    val sourceLiveData = MutableLiveData<HomeDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = HomeDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}