package com.wzq.jd.compose.app.data

import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.data.model.NetResult
import com.wzq.jd.compose.app.data.model.PagingResult
import com.wzq.jd.compose.app.data.model.SearchHotWords
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parametersOf

/**
 * create by wzq on 2023/12/1
 *
 */
class RemoteDataRepo(private val httpClient: HttpClient, private val baseUrl: String) {

    private val defaultErrorHandler = fun(exception: Throwable) {
        exception.printStackTrace()
    }

    suspend fun getArticleList(pageNum: Int = 0, cid: String? = null) = httpClient.runCatching {
        get("${baseUrl}article/list/${pageNum}/json") {
            url {
                if (!cid.isNullOrEmpty()) {
                    parameter("cid", cid)
                }
            }
        }.body<NetResult<PagingResult<ArticleItem>>>()
    }.onFailure(defaultErrorHandler)

    suspend fun getProjectList(pageNum: Int = 0) = httpClient.runCatching {
        get("${baseUrl}article/listproject/$pageNum/json").body<NetResult<PagingResult<ArticleItem>>>()
    }.onFailure(defaultErrorHandler)

    suspend fun getKnowledgeCategories() = runCatching {
        httpClient.get("${baseUrl}tree/json")
            .body<NetResult<List<Categories>>>()
    }.onFailure(defaultErrorHandler)


    suspend fun getHotWords() = runCatching {
        httpClient.get("${baseUrl}hotkey/json").body<NetResult<List<SearchHotWords>>>()
    }

    suspend fun getSearchResult(keywords: String) = runCatching {
        httpClient.submitForm(url = "${baseUrl}article/query/0/json", parametersOf("k", keywords))
            .body<NetResult<PagingResult<ArticleItem>>>()

    }.onFailure(defaultErrorHandler)
}