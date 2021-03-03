package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.Banner

/**
 * Created by wzq on 2019-07-12
 *
 */
data class BannerResult(
    val data: List<Banner>
) : BaseResult()
