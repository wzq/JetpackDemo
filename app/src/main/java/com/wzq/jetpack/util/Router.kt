package com.wzq.jetpack.util

import android.content.Context
import android.content.Intent
import com.wzq.jetpack.App
import com.wzq.jetpack.model.Category
import com.wzq.jetpack.ui.activity.CategoryActivity
import com.wzq.jetpack.ui.activity.LoginActivity
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

    fun go2login(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    fun go2Category(context: Context, category: Category) {
        val s = arrayListOf<Int>()
        category.children.forEach {
            s.add(it.id)
        }
        println(s)
        val t = Intent(context, CategoryActivity::class.java)
        t.putExtra("ids", s.toIntArray())
        t.putExtra("title", category.name)
        context.startActivity(t)
    }
}