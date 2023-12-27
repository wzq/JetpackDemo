package com.wzq.jd.compose.app.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wzq.jd.compose.app.data.model.ArticleItem

/**
 * create by wzq on 2023/12/26
 *
 */
class ProjectPageSource : PagingSource<Int, ArticleItem>() {
    /**
     * only refresh current page
     * or return a fixed num refresh all
     */
    override fun getRefreshKey(state: PagingState<Int, ArticleItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItem> {
        val position = params.key ?: 0
        val resp = DataRepos.remoteRepo.getProjectList(position)
        return resp.fold({ data ->
            LoadResult.Page(
                data = data.data.listData, prevKey = null, nextKey = position + 1
            )
        }, {
            LoadResult.Error(throwable = it)
        })
    }
}