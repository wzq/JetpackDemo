package com.wzq.jd.compose.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wzq.jd.compose.app.data.model.ArticleItem
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/12/20
 *
 */
@Database(entities = [ArticleItem::class, Categories::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun categoriesDao(): CategoriesDao
}
