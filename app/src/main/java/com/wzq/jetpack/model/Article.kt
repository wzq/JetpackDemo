package com.wzq.jetpack.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.wzq.jetpack.data.local.TagConverters
import com.wzq.jetpack.util.timeFormat


/**
 * Created by wzq on 2019-07-12
 *
 */
@Entity(tableName = "article")
data class Article(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    @PrimaryKey
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val projectLink: String,
    @Ignore
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    @Ignore
    val tags: MutableList<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    var top: String
) {
    fun getPublishTime(): String = timeFormat(publishTime)
}

data class Tag(
    val name: String,
    val url: String
)