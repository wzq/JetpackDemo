package com.wzq.jetpack.data.source

import androidx.paging.PagingSource
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.Article
import timber.log.Timber

/**
 * create by wzq on 2020/7/30
 *
 */
class ProjectPagerSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val result = Linker.api.getProjects(pageNum = params.key ?: 0)
            val nextPage = params.key?.let {
                if (it == result.data.pageCount) null else it + 1
            }
            Timber.i("next page num = $nextPage")
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
}
