package com.wzq.jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.result.TodoResult
import com.wzq.jetpack.util.thread.IOScope
import kotlinx.coroutines.launch

class TodoRepo: BaseRepo() {

    fun getTodoList(pageNum: Int, params: Map<String, String>): LiveData<TodoResult>{
        val data = MutableLiveData<TodoResult>()
        IOScope().launch {
            val result = Linker.api.getTodoList(pageNum, params)
            data.postValue(result)
        }
        return data
    }

        fun addTodo(params: Map<String, String >){
        IOScope().launch {
            val result = Linker.api.addTodo(params)
        }
    }
}