package com.wzq.sample.ui.main.project

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.data.paging.ProjectPagerSource
import com.wzq.sample.util.PAGE_SIZE

/**
 * create by wzq on 2021/4/22
 *
 */
class ProjectViewModel: ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val articleList = Pager(
        PagingConfig(PAGE_SIZE),
        0,
    ) {
        ProjectPagerSource()
    }
}