package com.wzq.sample.ui.main.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wzq.sample.data.local.AppDatabase
import com.wzq.sample.data.local.RemoteKey
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.model.ArticleList
import com.wzq.sample.data.remote.Linker

/**
 * create by wzq on 2021/4/14
 * db + network
 */
@ExperimentalPagingApi
class HomeRemoteMediator(private val db: AppDatabase) : RemoteMediator<Int, Article>() {

    private val tag = "home_data"

    private val articleDao = db.articleDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        println(loadType.name)
        return try {
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> 0 //页数从0开始
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(tag)
                    } // 实际不会为空
                    remoteKey.nextKey
                }
            }

            val remoteData = getDataFromRemote(loadKey)
            if (remoteData.datas.isNullOrEmpty()) {
                //数据为空 结束加载更多数据
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            //开始数据库事务
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    //刷新时清空数据
                    articleDao.clearAll()
                    remoteKeyDao.deleteByQuery(tag)
                }
                remoteKeyDao.insertOrReplace(RemoteKey(tag, loadKey + 1)) //更新远程建+1
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

    @Throws
    private suspend fun getDataFromRemote(loadKey: Int): ArticleList {
        val response = Linker.mainApi.getArticles(loadKey)
        return response.getOrThrow()
    }

}