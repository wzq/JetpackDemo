package com.wzq.jetpack.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wzq.jetpack.model.Article


/**
 * Created by wzq on 2019-07-23
 *
 */
@Dao
interface ArticleDao {

    @Query("select * from article ")
    fun getHomeArticle(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<Article>): List<Long>?
}