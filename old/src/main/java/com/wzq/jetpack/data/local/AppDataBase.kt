package com.wzq.jetpack.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wzq.jetpack.BaseInitializer
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner

/**
 * Created by wzq on 2019-07-23
 *
 */
const val DATABASE_NAME = "jetpack-demo.db"

@Database(entities = [Article::class, Banner::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun bannerDao(): BannerDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return getInstance(BaseInitializer.app)
        }

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            val builder = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
            return builder.build()
        }
    }
}
