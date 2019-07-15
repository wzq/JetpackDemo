package com.wzq.jetpack.model


/**
 * Created by wzq on 2019-07-15
 *
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)