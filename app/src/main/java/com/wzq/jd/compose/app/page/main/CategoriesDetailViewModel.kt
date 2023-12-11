package com.wzq.jd.compose.app.page.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/12/11
 *
 */
class CategoriesDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val initPosition = savedStateHandle["id"] ?: 0
    val categories: Categories? = savedStateHandle["data"]

    init {
        println(initPosition)
        println(categories)
    }

}