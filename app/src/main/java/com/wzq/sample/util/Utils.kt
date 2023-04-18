package com.wzq.sample.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

/**
 * Created by wzq on 2019-07-14
 *
 */

const val PAGE_SIZE = 20

fun timeFormat(time: Long): String {
    return DateFormat.format("yyyy-MM-dd", time).toString()
}

val Int.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

fun View.getStatusBarHeight(): Int {
    ViewCompat.getRootWindowInsets(this)?.also { insets ->
        val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        return systemInsets.top
    }
    return -1
}

fun Context.getActionBarHeight(): Int {
    val tv = TypedValue()
    if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return -1
}

fun AppCompatActivity.immersive() {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

fun AppCompatActivity.systemBarMode(isLightMode: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView).also {
        it.isAppearanceLightStatusBars = isLightMode
        it.isAppearanceLightNavigationBars = isLightMode
    }
}



/**
 * 适用于单线程环境
 *
 */
fun <T> simpleLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
