package com.wzq.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeViewModel internal constructor(private val repo: HomeRepo) : ViewModel() {

    private var flag = 0

    val banners = repo.getBanners()

    fun getArticles(pageNum: Int) = repo.getArticles(pageNum)

    val looper = object :MutableLiveData<Int>() {
        override fun onActive() {
            super.onActive()
            runLooper()
        }
    }

    private fun runLooper(){
        GlobalScope.launch {
            while (isActive && looper.hasActiveObservers()) {
                threadLog("$flag")
                looper.postValue(flag)
                flag++
                if(flag > 3) flag = 0
                delay(5000)
            }
        }
    }

}