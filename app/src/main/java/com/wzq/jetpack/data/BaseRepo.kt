package com.wzq.jetpack.data

import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.model.result.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


/**
 * Created by wzq on 2019-07-12
 *
 */
open class BaseRepo {

    suspend fun collectArticle(id: Int): BaseResult {
        return Linker.api.addCollectArticle(id)
    }

    suspend fun collectCancel(id: Int): BaseResult {
        return Linker.api.cancelCollectArticle(id)
    }
}