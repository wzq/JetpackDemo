package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.Category

/**
 * Created by wzq on 2019-07-24
 *
 */
data class CategoryResult(
    val data: List<Category>
) : BaseResult()
