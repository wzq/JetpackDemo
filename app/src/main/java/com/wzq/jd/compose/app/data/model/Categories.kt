package com.wzq.jd.compose.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "categories")
@Parcelize
@Serializable
data class Categories(
    val author: String,
    val courseId: Int,
    val cover: String,
    val desc: String,
    @PrimaryKey val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val type: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Parcelable {

    @Ignore
    var children: List<Categories> = emptyList()
}