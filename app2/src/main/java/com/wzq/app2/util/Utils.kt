package com.wzq.app2.util

import android.content.res.Resources
import android.text.format.DateFormat
import android.util.TypedValue

/**
 * Created by wzq on 2019-07-14
 *
 */
fun timeFormat(time: Long): String {
    return DateFormat.format("yyyy-MM-dd", time).toString()
}

val Int.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
