package com.wzq.jd.compose.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity (tableName = "articles")
@Parcelize
@Serializable
data class ArticleItem(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    @PrimaryKey val id: Int,
    val isAdminAdd: Boolean,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long?,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
//    val tags: List<String>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Parcelable