package com.wzq.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.source.TodoRepo
import com.wzq.jetpack.model.result.TodoResult

class TodoViewModel(val repo: TodoRepo) : ViewModel() {

    val todoList: LiveData<TodoResult> = repo.getTodoList(0, params = emptyMap())


    fun submit(params: Map<String, String>): LiveData<TodoResult> {
        return repo.addTodo(params)
    }

}