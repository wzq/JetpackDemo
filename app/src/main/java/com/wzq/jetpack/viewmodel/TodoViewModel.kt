package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wzq.jetpack.data.TodoRepo
import com.wzq.jetpack.model.result.TodoResult

class TodoViewModel(val repo: TodoRepo) : ViewModel() {

    val todoList: LiveData<TodoResult> = liveData {
        val result = repo.getTodoList(0, params = emptyMap())
        emit(result)
    }




    fun submit(params: Map<String, String>): LiveData<TodoResult> {
        return repo.addTodo(params)
    }

}