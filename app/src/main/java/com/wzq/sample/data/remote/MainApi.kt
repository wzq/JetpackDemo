package com.wzq.sample.data.remote

import com.wzq.sample.data.model.ArticleList
import com.wzq.sample.data.model.Category
import com.wzq.sample.data.model.DataResult
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * create by wzq on 2021/4/6
 *
 */
interface MainApi {

//    /**
//     * 获取轮播图
//     * http://www.wanandroid.com/banner/json
//     */
//    @GET("banner/json")
//    suspend fun getBanners(): BannerResult

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticles(@Path("pageNum") pageNum: Int): DataResult<ArticleList>

    @GET("article/listproject/{pageNum}/json")
    suspend fun getProjects(@Path("pageNum") pageNum: Int): DataResult<ArticleList>

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    fun getCategory(): DataResult<List<Category>>
}