package com.wzq.jetpack.viewmodel

import androidx.lifecycle.*
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.result.GankItem
import com.wzq.jetpack.model.result.GankResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GankViewModel internal constructor(): ViewModel() {

    private val gankResult  = MutableLiveData<GankResult>()
    val dailyList: LiveData<List<GankItem>> = gankResult.map {
        it.results.android.reversed()
    }

    fun getDailyInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = Linker.gankApi.getDaliyInfo()
            gankResult.postValue(data)
        }
    }

}