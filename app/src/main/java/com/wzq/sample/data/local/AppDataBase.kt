package com.wzq.sample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wzq.sample.App
import com.wzq.sample.data.model.Article

/**
 * create by wzq on 2021/4/9
 *
 */
@Database(entities = [Article::class, RemoteKey::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {

        private const val DATABASE_NAME = "my-sample.db"

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * 注意：如果您的应用在单个进程中运行，在实例化 AppDatabase 对象时应遵循单例设计模式。每个 RoomDatabase 实例的成本相当高，而您几乎不需要在单个进程中访问多个实例。
         * 如果您的应用在多个进程中运行，请在数据库构建器调用中包含 enableMultiInstanceInvalidation()...
         * https://developer.android.google.cn/training/data-storage/room?hl=zh_cn
         */
        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(App.context).also { instance = it }
            }
        }

        /**
         *  fallbackToDestructiveMigration 如果未找到表改动合并策略，则重建表，数据会丢失
         */
        private fun buildDatabase(context: Context): AppDatabase {
            val builder = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
            return builder.build()
        }
    }
}