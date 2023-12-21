package com.wzq.jd.compose.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wzq.jd.compose.app.data.model.ArticleItem

/**
 * create by wzq on 2023/12/20
 *
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<ArticleItem>)

    @Query("select * from articles where chapterId = :cid")
    suspend fun getArticlesByCid(cid: Int): List<ArticleItem>

    @Query("select * from articles")
    suspend fun getArticlesAll(): List<ArticleItem>
}