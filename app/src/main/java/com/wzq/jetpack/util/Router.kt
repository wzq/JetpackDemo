package com.wzq.jetpack.util

import android.content.Context
import android.content.Intent
import com.wzq.jetpack.ui.activity.WebActivity


/**
 * Created by wzq on 2019-07-15
 *
 */
object Router {

    fun go2web(context: Context, url: String){
        val p = Intent(context, WebActivity::class.java)
        p.putExtra("url", url)
        context.startActivity(p)
    }
}