package com.wzq.sample.data

import com.wzq.sample.data.remote.Linker

/**
 * create by wzq on 2021/4/23
 *
 */
class MainRepo {
    suspend fun getCategory() = Linker.mainApi.runCatching {
        getCategory()
    }

    suspend fun getBanner() = Linker.mainApi.runCatching {
        getBanners()
    }

}