package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.UserRepo
import com.wzq.jetpack.model.result.LoginResult


/**
 * Created by wzq on 2019-07-12
 *
 */
class LoginViewModel internal constructor(private val repo: UserRepo): ViewModel() {


    fun login(account:String, password: String): LiveData<LoginResult?> {
        return repo.login(account, password)
    }
}