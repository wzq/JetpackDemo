@file:OptIn(ExperimentalPagingApi::class)

package com.wzq.sample.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.local.RemoteKey
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.remote.Linker

class ArticleRemoteMediator(
    private val label: String,
    private val db: AppDatabase,
) : RemoteMediator<Int, Article>() {

    private val articleDao = db.articleDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> 0 //页数从0开始
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.keyByQuery(this.label)
                    }
                    remoteKey.nextKey
                }
            }

            val remoteData = Linker.mainApi.getArticles(loadKey).data
            if (remoteData.datas.isEmpty()) {
                //数据为空 结束加载更多
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            //开始数据库事务
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    //刷新时清空数据
                    articleDao.clearAll()
                    remoteKeyDao.deleteByQuery(this.label)
                }
                remoteKeyDao.insertOrReplace(RemoteKey(this.label, loadKey + 1)) //更新远程建+1
                articleDao.insertAll(remoteData.datas) //添加数据库内容
            }

            //是否已经最后一页
            val isEndOfBounds = remoteData.curPage == remoteData.pageCount
            MediatorResult.Success(
                endOfPaginationReached = isEndOfBounds
            )
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

}