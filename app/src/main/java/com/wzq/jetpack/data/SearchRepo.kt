package com.wzq.jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.HotKey
import com.wzq.jetpack.util.thread.IOScope
import kotlinx.coroutines.launch

class SearchRepo : BaseRepo() {

    fun getHotWords(): LiveData<List<HotKey>> {
        val data = MutableLiveData<List<HotKey>>()
        IOScope().launch {
            val result = Linker.api.getHotSearchData()
            data.postValue(result.data)
        }
        return data
    }


    suspend fun searchAny(key: String, page: Int) =
        Linker.api.queryBySearchKey(page, key)


}