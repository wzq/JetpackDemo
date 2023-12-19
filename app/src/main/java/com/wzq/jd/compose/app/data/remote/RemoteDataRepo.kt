package com.wzq.jd.compose.app.data.remote

import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories
import com.wzq.jd.compose.app.data.model.HotWords
import com.wzq.jd.compose.app.data.model.NetResult
import com.wzq.jd.compose.app.data.model.PagingResult
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parametersOf

/**
 * create by wzq on 2023/12/1
 *
 */
class RemoteDataRepo(
    private val baseUrl: String = "https://www.wanandroid.com/",
    private val client: CustomHttpClient
) {

    suspend fun getArticleList(pageNum: Int = 0, cid: Int? = null) =
        client.doRequest<NetResult<PagingResult<ArticleItem>>> {
            get("${baseUrl}article/list/${pageNum}/json") {
                if (cid != null) this.parameter("cid", cid)
            }.body()
        }

    suspend fun getProjectList(pageNum: Int = 0) =
        client.doRequest<NetResult<PagingResult<ArticleItem>>> { get("${baseUrl}article/listproject/$pageNum/json").body() }

    suspend fun getKnowledgeCategories(): Result<NetResult<List<Categories>>> =
        client.doRequest { get("${baseUrl}tree/json").body() }

    suspend fun getHotWords() = client.doRequest<NetResult<List<HotWords>>> {
        get("${baseUrl}hotkey/json").body()
    }

    suspend fun getSearchResult(keywords: String) = client.doRequest {
        submitForm(
            "${baseUrl}article/query/0/json",
            parametersOf("k", keywords)
        ).body<NetResult<PagingResult<ArticleItem>>>()
    }

    suspend fun test() = client.doRequest<Unit> {
        this.post("") {
            setBody(parametersOf("a", "b"))
        }
    }
}