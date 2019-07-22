package com.wzq.jetpack.util

import android.content.Context
import android.text.format.DateFormat
import android.util.TypedValue
import com.wzq.jetpack.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors


/**
 * Created by wzq on 2019-07-14
 *
 */

val DISK_IO = Executors.newSingleThreadExecutor()

// thread pool used for network requests
val NETWORK_IO = Executors.newFixedThreadPool(5)

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


fun <T> resultFactory(action: (T?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            action(response.body())
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
        }

    }
}