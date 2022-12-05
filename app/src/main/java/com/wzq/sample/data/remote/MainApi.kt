package com.wzq.sample.data.remote

import com.google.gson.JsonElement
import com.wzq.sample.data.model.ArticleList
import com.wzq.sample.data.model.Banner
import com.wzq.sample.data.model.Category
import com.wzq.sample.data.model.Response
import retrofit2.http.*


/**
 * create by wzq on 2021/4/6
 *
 */
interface MainApi {

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanners(): Response<List<Banner>>

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticles(@Path("pageNum") pageNum: Int): Response<ArticleList>

    @GET("article/listproject/{pageNum}/json")
    suspend fun getProjects(@Path("pageNum") pageNum: Int): Response<ArticleList>

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    suspend fun getCategory(): Response<List<Category>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    suspend fun getCategoryArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Response<ArticleList>

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<JsonElement>

//    /**
//     * 搜索热词
//     * http://www.wanandroid.com/hotkey/json
//     */
//    @GET("hotkey/json")
//    suspend fun getHotSearchData(): HotKeyResult

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page
     * @param key
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun queryBySearchKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): Response<ArticleList>
}