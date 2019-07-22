package com.wzq.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.wzq.jetpack.data.ProjectRepo
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Listing


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
    }
}