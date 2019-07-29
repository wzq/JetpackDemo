package com.wzq.jetpack.model.result

import com.wzq.jetpack.model.HotKey


/**
 * Created by wzq on 2019-07-29
 *
 */
data class HotKeyResult(
    val data: List<HotKey>
): BaseResult()