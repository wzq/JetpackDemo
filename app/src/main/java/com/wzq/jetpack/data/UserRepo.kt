package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.User
import com.wzq.jetpack.model.result.LoginResult
import com.wzq.jetpack.util.resultFactory

/**
 * Created by wzq on 2019-07-16
 */
class UserRepo: BaseRepo(){

    fun login(account:String, password: String): LiveData<LoginResult?> {
        val data = MutableLiveData<LoginResult?>()
        Linker.api.loginWanAndroid(account, password).enqueue(resultFactory {
            data.value = it
        })
        return data
    }

}
