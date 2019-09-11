package com.wzq.jetpack.util

import android.content.Context
import android.text.format.DateFormat
import android.util.TypedValue
import com.wzq.jetpack.App
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext


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

fun threadLog(v: String){
    Timber.d("[${Thread.currentThread().name}] -> $v")
}