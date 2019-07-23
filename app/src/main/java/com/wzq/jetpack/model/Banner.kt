package com.wzq.jetpack.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by wzq on 2019-07-15
 *
 */
@Entity(tableName = "banner")
data class Banner(
    val desc: String,
    @PrimaryKey
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)