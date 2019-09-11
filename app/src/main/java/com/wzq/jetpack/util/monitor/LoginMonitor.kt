package com.wzq.jetpack.util.monitor

import androidx.lifecycle.LiveData
import com.wzq.jetpack.model.result.LoginResult
import com.wzq.jetpack.util.Preference
import com.wzq.jetpack.util.Prefs
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by wzq on 2019-07-26
 *
 */
class LoginMonitor: LiveData<String?>() {

    private val userName by Preference(Prefs.USER_NAME, "点击登录")


    override fun onActive() {
        super.onActive()
        value = userName
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserLogin(result: LoginResult) {
        postValue(result.data.username)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserLogout(result: LoginResult) {
        postValue("点击登录")
    }

    fun isLogin(): Boolean = userName != "点击登录"

}