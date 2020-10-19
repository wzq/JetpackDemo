package com.wzq.jetpack.util

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
import kotlin.reflect.KClass


/**
 * Created by wzq on 2019-07-16
 *
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (content.isNotBlank()) Toast.makeText(this, content, duration).show()
}

fun AppCompatActivity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val option =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}


fun Context.openPage(clazz: KClass<AppCompatActivity>, args: Bundle? = null, reqCode: Int = -1) {
    val intent = Intent(this, clazz.java)
    args?.also { intent.putExtra("args", args) }
    if (this is Activity && reqCode > -1) {
        startActivityForResult(intent, reqCode)
    } else {
        startActivity(intent)
    }
}