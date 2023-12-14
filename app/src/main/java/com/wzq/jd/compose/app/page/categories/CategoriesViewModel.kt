package com.wzq.jd.compose.app.page.categories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wzq.jd.compose.app.data.model.Categories

/**
 * create by wzq on 2023/12/11
 *
 */
class CategoriesViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val initPosition = savedStateHandle["position"] ?: 0
    val categories: Categories? = savedStateHandle["data"]

    init {
        println("a----$categories")
        println("${initPosition}----asdasdasd")
    }

}