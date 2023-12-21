package com.wzq.jd.compose.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wzq.jd.compose.app.data.model.ArticleItem

/**
 * create by wzq on 2023/12/20
 *
 */
@Database(entities = [ArticleItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
