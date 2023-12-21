package com.wzq.jd.compose.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/12/21
 *
 */
@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<Categories>)

    @Query("Select * from categories")
    suspend fun getCategoriesAll(): List<Categories>
}