package com.wzq.jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.result.TodoResult
import com.wzq.jetpack.util.thread.IOScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoRepo : BaseRepo() {

//    fun getTodoList(pageNum: Int, params: Map<String, String>): LiveData<TodoResult> {
//        val data = MutableLiveData<TodoResult>()
//        IOScope().launch {
//            val result = Linker.api.getTodoList(pageNum, params)
//            data.postValue(result)
//        }
//        return data
//    }

    fun addTodo(params: Map<String, String>): LiveData<TodoResult> {
        val data = MutableLiveData<TodoResult>()
        IOScope().launch {
            val result = Linker.api.addTodo(params)
            data.postValue(result)
        }
        return data
    }


    suspend fun getTodoList(pageNum: Int, params: Map<String, String>) =
        withContext(Dispatchers.IO) {
            Linker.api.getTodoList(pageNum, params)
        }
}