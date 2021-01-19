package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.data.remote.RespCallback
import com.wzq.jetpack.model.result.LoginResult

/**
 * Created by wzq on 2019-07-16
 */
class UserRepo: BaseRepo(){

    fun login(account:String, password: String): LiveData<LoginResult?> {
        val data = MutableLiveData<LoginResult?>()
        Linker.api.loginWanAndroid(account, password).enqueue(RespCallback {
            data.value = it.getOrNull()
        })
        return data
    }

}
