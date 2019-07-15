package com.wzq.jetpack.util

import android.content.Context
import android.text.format.DateFormat
import android.util.TypedValue
import com.wzq.jetpack.App


/**
 * Created by wzq on 2019-07-14
 *
 */

fun timeFormat(time: Long): String {
    return DateFormat.format("yyyy-MM-dd", time).toString()
}


fun dp2px(context: Context?, dp: Int): Int {
    if (context == null) return 0
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}