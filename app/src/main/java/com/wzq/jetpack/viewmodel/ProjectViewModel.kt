package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.ProjectRepo


/**
 * Created by wzq on 2019-07-22
 *
 */
class ProjectViewModel internal constructor(repo: ProjectRepo) : ViewModel() {
    private val repoResult = repo.fetchLastProject()

    val listData = repoResult.pagedList

    val networkState =  repoResult.networkState

    val refreshState = repoResult.refreshState

    fun refresh() = repoResult.refresh()

    fun retry() {
        repoResult.retry()

        //todo
    }
}