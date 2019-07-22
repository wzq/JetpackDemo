package com.wzq.jetpack.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wzq.jetpack.model.Article


/**
 * Created by wzq on 2019-07-22
 *
 */
class DataSourceFactory(): DataSource.Factory<Int, Article>() {
    val sourceLiveData = MutableLiveData<ProjectDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = ProjectDataSource()
        sourceLiveData.postValue(source)
        return source
    }


}