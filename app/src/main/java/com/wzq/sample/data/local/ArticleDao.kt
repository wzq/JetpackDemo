package com.wzq.sample.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wzq.sample.data.model.Article

/**
 * create by wzq on 2021/4/9
 *
 */
@Dao
interface ArticleDao {

    /**
     * 添加文章列表 如果重复则替换
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("select * from article")
    fun getArticles(): List<Article>

    @Query("delete from article")
    fun clearAll()

    @Query("select * from article order by publishTime desc")
    fun getPagingArticles(): PagingSource<Int, Article>
}