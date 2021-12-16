package com.wzq.sample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.remote.Linker

class ProjectPagerSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val result = Linker.mainApi.getProjects(pageNum = params.key ?: 0)
            val nextPage = params.key?.let {
                if (it == result.data.pageCount) null else it + 1
            }
            LoadResult.Page(
                data = result.data.datas,
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