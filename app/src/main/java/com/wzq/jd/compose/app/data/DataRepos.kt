package com.wzq.jd.compose.app.data

import com.wzq.jd.compose.app.data.remote.CustomHttpClient
import com.wzq.jd.compose.app.data.remote.RemoteDataRepo

/**
 * create by wzq on 2023/11/27
 *
 */
object DataRepos {

    val remoteRepo by lazy { RemoteDataRepo(client = CustomHttpClient()) }

}