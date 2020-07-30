package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.wzq.jetpack.data.ProjectRepo
import com.wzq.jetpack.data.source.ProjectPagerSource


/**
 * Created by wzq on 2019-07-22
 *
 */
class ProjectViewModel internal constructor(repo: ProjectRepo) : ViewModel() {

    fun fetchLastProject() = Pager(
        PagingConfig(20),
        0 //first page num
    ) {
        ProjectPagerSource()
    }.liveData
}