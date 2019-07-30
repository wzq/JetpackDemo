package com.wzq.jetpack.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner


/**
 * Created by wzq on 2019-07-23
 *
 */
@Dao
interface BannerDao {

    @Query("select * from banner order by id asc")
    fun getHomeBanner(): List<Banner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(banner: List<Banner>): List<Long>?

}