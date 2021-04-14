package com.wzq.sample.ui.main.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.local.RemoteKey
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.remote.Linker

/**
 * create by wzq on 2021/4/14
 * db + network
 */
@ExperimentalPagingApi
class HomeRemoteMediator(val db: AppDatabase) : RemoteMediator<Int, Article>() {

    val tag = "home_data"

    val articleDao = db.articleDao()
    val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        println(loadType.name)
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(tag)
                    }
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextKey
                }
            } ?: 0

            val response = Linker.mainApi.getArticles(loadKey)

            val data = response.getOrThrow()
            if (data.datas.isNullOrEmpty()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearAll()
                    remoteKeyDao.deleteByQuery(tag)
                }
                val nextKey = loadKey + 1
                remoteKeyDao.insertOrReplace(RemoteKey(tag, nextKey))

                articleDao.insertAll(data.datas)
            }
            MediatorResult.Success(
                endOfPaginationReached = data.curPage == 10
            )
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.SKIP_INITIAL_REFRESH
//    }
}