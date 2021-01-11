package com.wzq.jetpack.model.result

import com.google.gson.annotations.SerializedName


data class GankResult(
    @SerializedName("category")
    val category: List<String>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("results")
    val results: GankData
)

data class GankData(
    @SerializedName("Android")
    val android: List<GankItem>,
    @SerializedName("App")
    val app: List<GankItem>,
    @SerializedName("iOS")
    val iOS: List<GankItem>,
    @SerializedName("休息视频")
    val restVideo: List<GankItem>,
    @SerializedName("前端")
    val web: List<GankItem>,
    @SerializedName("拓展资源")
    val extResource: List<GankItem>,
    @SerializedName("瞎推荐")
    val other: List<GankItem>,
    @SerializedName("福利")
    val welfare: List<GankItem>
)

data class GankItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("used")
    val used: Boolean,
    @SerializedName("who")
    val who: String
)
//@JsonClass(generateAdapter = true)
//data class GankItem(
//    val createdAt: String?,
//    val desc: String?,
//    @Json(name = "_id")
//    val id: String?,
//    val publishedAt: String?,
//    val source: String?,
//    val type: String?,
//    val url: String?,
//    val used: Boolean?,
//    val who: String?
//)