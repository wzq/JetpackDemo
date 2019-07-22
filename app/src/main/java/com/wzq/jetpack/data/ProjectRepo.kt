package com.wzq.jetpack.data

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Listing
import com.wzq.jetpack.util.NETWORK_IO
import com.wzq.jetpack.data.source.DataSourceFactory


/**
 * Created by wzq on 2019-07-22
 *
 */
class ProjectRepo: BaseRepo() {

    fun fetchLastProject(): Listing<Article>{
        val sourceFactory = DataSourceFactory()

        val pagedList = sourceFactory.toLiveData(20, fetchExecutor = NETWORK_IO)

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        return Listing(
            pagedList = pagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData){
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {sourceFactory.sourceLiveData.value?.invalidate()},
            refreshState = refreshState
        )
    }
}