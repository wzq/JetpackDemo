package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.User

/**
 * Created by wzq on 2019-07-16
 *
 */
data class LoginResult(
    val data: User
) : BaseResult()
