package com.wzq.jetpack.util.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlin.reflect.KClass

/**
 * Created by wzq on 2019-07-16
 *
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (content.isNotBlank()) Toast.makeText(this, content, duration).show()
}

fun AppCompatActivity.systemBarMode(isLightMode: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView)?.also {
        it.isAppearanceLightStatusBars = !isLightMode
        it.isAppearanceLightNavigationBars = !isLightMode
    }
}

fun AppCompatActivity.immersive() {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

fun Context.openPage(
    clazz: KClass<out AppCompatActivity>,
    args: Bundle? = null,
    reqCode: Int = -1
) {
    val intent = Intent(this, clazz.java)
    if (args != null) intent.putExtras(args)
    if (this is Activity && reqCode > -1) {
        startActivityForResult(intent, reqCode)
    } else {
        startActivity(intent)
    }
}

@Deprecated("use immersive() ")
fun AppCompatActivity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    } else {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}
