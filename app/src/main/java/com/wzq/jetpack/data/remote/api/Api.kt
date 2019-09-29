package com.wzq.jetpack.data.remote.api

import com.google.gson.JsonObject
import com.wzq.jetpack.model.Todo
import com.wzq.jetpack.model.result.*
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by wzq on 2019-07-12
 *
 */
interface Api {

        /**
         * 获取轮播图
         * http://www.wanandroid.com/banner/json
         */
        @GET("banner/json")
        fun getBanners(): Call<BannerResult>

        /**
         * 获取首页置顶文章列表
         * http://www.wanandroid.com/article/top/json
         */
//        @GET("article/top/json")
//        fun getTopArticles(): Observable<HttpResult<MutableList<Article>>>

        /**
         * 获取文章列表
         * http://www.wanandroid.com/article/list/0/json
         * @param pageNum
         */
        @GET("article/list/{pageNum}/json")
        suspend fun getArticles(@Path("pageNum") pageNum: Int): ArticleResult?

        @GET("article/listproject/{pageNum}/json")
        fun getLastProjects(@Path("pageNum") pageNum: Int): Call<ArticleResult>

        /**
         * 获取知识体系
         * http://www.wanandroid.com/tree/json
         */
        @GET("tree/json")
        fun getCategory(): Call<CategoryResult>

        /**
         * 知识体系下的文章
         * http://www.wanandroid.com/article/list/0/json?cid=168
         * @param page
         * @param cid
         */
        @GET("article/list/{page}/json")
        fun getCategoryArticles(@Path("page") page: Int, @Query("cid") cid: Int): Call<ArticleResult>

//        /**
//         * 导航数据
//         * http://www.wanandroid.com/navi/json
//         */
//        @GET("navi/json")
//        fun getNavigationList(): Observable<HttpResult<List<NavigationBean>>>
//
//        /**
//         * 项目数据
//         * http://www.wanandroid.com/project/tree/json
//         */
//        @GET("project/tree/json")
//        fun getProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>>
//
//        /**
//         * 项目列表数据
//         * http://www.wanandroid.com/project/list/1/json?cid=294
//         * @param page
//         * @param cid
//         */
//        @GET("project/list/{page}/json")
//        fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<HttpResult<ArticleResponseBody>>
//
        /**
         * 登录
         * http://www.wanandroid.com/user/login
         * @param username
         * @param password
         */
        @POST("user/login")
        @FormUrlEncoded
        fun loginWanAndroid(@Field("username") username: String,
                            @Field("password") password: String): Call<LoginResult>
//
//        /**
//         * 注册
//         * http://www.wanandroid.com/user/register
//         * @param username
//         * @param password
//         * @param repassword
//         */
//        @POST("user/register")
//        @FormUrlEncoded
//        fun registerWanAndroid(@Field("username") username: String,
//                               @Field("password") password: String,
//                               @Field("repassword") repassword: String): Observable<HttpResult<LoginData>>
//
//        /**
//         * 退出登录
//         * http://www.wanandroid.com/user/logout/json
//         */
//        @GET("user/logout/json")
//        fun logout(): Observable<HttpResult<Any>>
//
        /**
         *  获取收藏列表
         *  http://www.wanandroid.com/lg/collect/list/0/json
         *  @param page
         */
        @GET("lg/collect/list/{page}/json")
        suspend fun getCollectList(@Path("page") page: Int): ArticleResult

        /**
         * 收藏站内文章
         * http://www.wanandroid.com/lg/collect/1165/json
         * @param id article id
         */
        @POST("lg/collect/{id}/json")
        suspend fun addCollectArticle(@Path("id") id: Int): BaseResult
//
//        /**
//         * 收藏站外文章
//         * http://www.wanandroid.com/lg/collect/add/json
//         * @param title
//         * @param author
//         * @param link
//         */
//        @POST("lg/collect/add/json")
//        @FormUrlEncoded
//        fun addCoolectOutsideArticle(@Field("title") title: String,
//                                     @Field("author") author: String,
//                                     @Field("link") link: String): Observable<HttpResult<Any>>
//
        /**
         * 文章列表中取消收藏文章
         * http://www.wanandroid.com/lg/uncollect_originId/2333/json
         * @param id
         */
        @POST("lg/uncollect_originId/{id}/json")
        suspend fun cancelCollectArticle(@Path("id") id: Int): BaseResult
//
//        /**
//         * 收藏列表中取消收藏文章
//         * http://www.wanandroid.com/lg/uncollect/2805/json
//         * @param id
//         * @param originId
//         */
//        @POST("lg/uncollect/{id}/json")
//        @FormUrlEncoded
//        fun removeCollectArticle(@Path("id") id: Int,
//                                 @Field("originId") originId: Int = -1): Observable<HttpResult<Any>>
//
        /**
         * 搜索热词
         * http://www.wanandroid.com/hotkey/json
         */
        @GET("hotkey/json")
        suspend fun getHotSearchData(): HotKeyResult

        /**
         * 搜索
         * http://www.wanandroid.com/article/query/0/json
         * @param page
         * @param key
         */
        @POST("article/query/{page}/json")
        @FormUrlEncoded
        suspend fun queryBySearchKey(@Path("page") page: Int,
                             @Field("k") key: String): ArticleResult

        /**
         * V2版本 ： 获取TODO列表数据
         * http://www.wanandroid.com/lg/todo/v2/list/页码/json
         * @param page 页码从1开始，拼接在 url 上
         * @param map
         *          status 状态， 1-完成；0未完成; 默认全部展示；
         *          type 创建时传入的类型, 默认全部展示
         *          priority 创建时传入的优先级；默认全部展示
         *          orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
         */
        @GET("/lg/todo/v2/list/{page}/json")
        suspend fun getTodoList(@Path("page") page: Int, @QueryMap map: Map<String, String>): TodoResult

        /**
         * Todo完成
         * http://www.wanandroid.com/lg/todo/done/80/json
         * @param id 拼接在链接上，为唯一标识
         * @param status 0或1，传1代表未完成到已完成，反之则反之
         */
        @POST("/lg/todo/done/{id}/json")
        @FormUrlEncoded
        suspend fun updateTodoById(@Path("id") id: Int, @Field("status") status: Int): JsonObject
        /**
         * 删除一条Todo
         * http://www.wanandroid.com/lg/todo/delete/83/json
         * @param id
         */
        @POST("/lg/todo/delete/{id}/json")
        suspend fun deleteTodoById(@Path("id") id: Int): JsonObject

        /**
         * 新增一条Todo
         * http://www.wanandroid.com/lg/todo/add/json
         * @param body
         *          title: 新增标题
         *          content: 新增详情
         *          date: 2018-08-01
         *          type: 0
         */
        @POST("/lg/todo/add/json")
        @FormUrlEncoded
        suspend fun addTodo(@FieldMap map: Map<String, String>): TodoResult

        /**
         * 更新一条Todo内容
         * http://www.wanandroid.com/lg/todo/update/83/json
         * @param body
         *          title: 新增标题
         *          content: 新增详情
         *          date: 2018-08-01
         *          status: 0 // 0为未完成，1为完成
         *          type: 0
         */
        @POST("/lg/todo/update/{id}/json")
        @FormUrlEncoded
        fun updateTodo(@Path("id") id: Int, @FieldMap map: MutableMap<String, Any>): JsonObject
//
//        /**
//         * 获取公众号列表
//         * http://wanandroid.com/wxarticle/chapters/json
//         */
//        @GET("/wxarticle/chapters/json")
//        fun getWXChapters(): Observable<HttpResult<MutableList<WXChapterBean>>>
//
//        /**
//         * 查看某个公众号历史数据
//         * http://wanandroid.com/wxarticle/list/405/1/json
//         * @param id 公众号 ID
//         * @param page 公众号页码
//         */
//        @GET("/wxarticle/list/{id}/{page}/json")
//        fun getWXArticles(@Path("id") id: Int,
//                          @Path("page") page: Int): Observable<HttpResult<ArticleResponseBody>>
//
//        /**
//         * 在某个公众号中搜索历史文章
//         * http://wanandroid.com/wxarticle/list/405/1/json?k=Java
//         * @param id 公众号 ID
//         * @param key 搜索关键字
//         * @param page 公众号页码
//         */
//        @GET("/wxarticle/list/{id}/{page}/json")
//        fun queryWXArticles(@Path("id") id: Int,
//                            @Query("k") key: String,
//                            @Path("page") page: Int): Observable<HttpResult<ArticleResponseBody>>

}