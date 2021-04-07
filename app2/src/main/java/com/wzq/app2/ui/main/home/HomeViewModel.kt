package com.wzq.app2.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.wzq.app2.ui.main.home.HomePagerSource
import com.wzq.app2.util.PAGE_SIZE

/**
 * create by wzq on 2021/4/6
 *
 */
class HomeViewModel: ViewModel() {

    val articleList = Pager(PagingConfig(PAGE_SIZE), 0) {
        HomePagerSource()
    }

}