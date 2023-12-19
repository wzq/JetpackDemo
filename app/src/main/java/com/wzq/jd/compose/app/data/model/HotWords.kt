package com.wzq.jd.compose.app.data.model

import kotlinx.serialization.Serializable

/**
 * create by wzq on 2023/12/5
 *
 */
@Serializable
data class HotWords(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)