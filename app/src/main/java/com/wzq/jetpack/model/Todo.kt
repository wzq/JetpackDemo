package com.wzq.jetpack.model
import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("completeDate")
    val completeDate: Any,
    @SerializedName("completeDateStr")
    val completeDateStr: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: Long,
    @SerializedName("dateStr")
    val dateStr: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("userId")
    val userId: Int
)