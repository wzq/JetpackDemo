package com.wzq.jetpack.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import timber.log.Timber

/**
 * create by wzq on 2020/7/30
 *
 */
class CollectPagerSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val result = Linker.api.getCollectList(params.key ?: 0).data!!
            val nextPage = params.key?.let {
                if (it + 1 >= result.pageCount) null else it + 1
            }
            Timber.i("next page num = $nextPage")
            LoadResult.Page(
                data = result.datas,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}
