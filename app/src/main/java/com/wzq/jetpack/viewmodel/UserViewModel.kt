package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.wzq.jetpack.data.source.CollectPagerSource

/**
 * create by wzq on 2021/1/14
 *
 */
class UserViewModel : ViewModel() {

    val collectList = Pager(PagingConfig(20), 0) {
        CollectPagerSource()
    }.liveData
}