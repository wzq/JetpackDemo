package com.wzq.sample.data.local

import androidx.room.*

/**
 * create by wzq on 2021/4/14
 *
 */
@Entity(tableName = "remote_keys")
data class RemoteKey(@PrimaryKey val label: String, val nextKey: Int)

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE label = :query")
    suspend fun keyByQuery(query: String): RemoteKey

    @Query("DELETE FROM remote_keys WHERE label = :query")
    suspend fun deleteByQuery(query: String)
}