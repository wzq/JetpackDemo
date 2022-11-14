package com.wzq.sample.data

import com.wzq.sample.data.model.ArticleList
import com.wzq.sample.data.model.Response
import com.wzq.sample.data.remote.Linker

/**
 * create by wzq on 2021/4/23
 *
 */
class MainRepo {

    suspend fun getProjects(pageNum: Int = 0): Result<Response<ArticleList>> {
        return Result.runCatch {
            Linker.mainApi.getProjects(pageNum)
        }
    }

    suspend fun getHomeArticles(pageNum: Int = 0): Result<Response<ArticleList>> {
        return Result.runCatch {
            Linker.mainApi.getArticles(pageNum)
        }
    }


    suspend fun getCategory() = Result.runCatch {
        Linker.mainApi.getCategory()
    }

    suspend fun getBanner() = Result.runCatch {
        Linker.mainApi.getBanners()
    }


}