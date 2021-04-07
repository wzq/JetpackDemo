package com.wzq.jetpack.model

/**
 * Created by wzq on 2019-07-16
 *
 */

// {"data":{"admin":false,"chapterTops":[],"collectIds":[],"email":"","icon":"","id":27233,"nickname":"aywzq37","password":"","token":"","type":0,"username":"aywzq37"},"errorCode":0,"errorMsg":""}
data class User(
    val collectIds: List<Int>,
    val admin: Boolean,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val token: String,
    val type: Int,
    val username: String

)
