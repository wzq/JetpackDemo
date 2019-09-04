package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.SearchRepo

class SearchViewModel internal constructor(val repo: SearchRepo): ViewModel() {

    val hotWords = repo.getHotWords()
}