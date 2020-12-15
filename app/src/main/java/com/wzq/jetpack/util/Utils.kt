package com.wzq.jetpack.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.text.format.DateFormat
import android.util.TypedValue
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.Executors


/**
 * Created by wzq on 2019-07-14
 *
 */

const val WEIBO_KEY = "1820619456"

val DISK_IO = Executors.newSingleThreadExecutor()

// thread pool used for network requests
val NETWORK_IO = Executors.newFixedThreadPool(5)

fun timeFormat(time: Long): String {
    return DateFormat.format("yyyy-MM-dd", time).toString()
}


fun dp2px(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        Resources.getSystem().displayMetrics
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

fun threadLog(v: String) {
    Timber.d("[${Thread.currentThread().name}] -> $v")
}

/**
 * 对未绘制的VIEW 进行截图
 * @return Bitmap
 */
fun captureView(view: View, w: Int, h: Int): Bitmap {
    view.measure(
        View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
    view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawColor(Color.WHITE)
    view.draw(canvas)
    return bitmap
}