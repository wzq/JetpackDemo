package com.wzq.jd.compose.app.data

import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * create by wzq on 2023/12/1
 *
 */
object RemoteDataRepo {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private const val HOME_ARTICLE_LIST = "${BASE_URL}article/list/0/json"
    private const val KNOWLEDGE_CATEGORIES = "${BASE_URL}tree/json"
    private const val PROJECT_LIST = "${BASE_URL}article/listproject/0/json"

    private val httpClient get() = NetworkUtil.client

    private val defaultErrorHandler = fun(exception: Throwable) {
        exception.printStackTrace()
    }

    suspend fun getHomeArticleList() = httpClient.runCatching {
        get(HOME_ARTICLE_LIST).body<NetResultList<ArticleItem>>()
    }.onFailure(defaultErrorHandler)

    suspend fun getProjectList() = httpClient.runCatching {
        get(PROJECT_LIST).body<NetResultList<ArticleItem>>()
    }.onFailure(defaultErrorHandler)

    suspend fun getKnowledgeCategories() = runCatching {
        httpClient.get(KNOWLEDGE_CATEGORIES).body<NetResultList<KnowledgeCategories>>()
    }.onFailure(defaultErrorHandler)
}