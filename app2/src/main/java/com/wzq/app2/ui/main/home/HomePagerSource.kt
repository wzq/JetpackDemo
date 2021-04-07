package com.wzq.app2.ui.main.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wzq.app2.data.model.Article
import com.wzq.app2.data.remote.Linker

/**
 * create by wzq on 2020/7/30
 *
 */
class HomePagerSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val pageNum = params.key ?: 0
            val result = Linker.mainApi.getArticles(pageNum).getOrThrow()
            val nextPage = result.let {
                if (it.curPage >= it.pageCount) {
                    null
                } else {
                    pageNum + 1
                }
            }
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
