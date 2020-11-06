package com.wzq.jetpack.util

import android.content.Context
import android.content.Intent
import com.wzq.jetpack.model.Category
import com.wzq.jetpack.test.TestActivity
import com.wzq.jetpack.ui.activity.*


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


    fun go2category(context: Context, category: Category){
        val intent = Intent(context, CategoryActivity::class.java)


        val s1 = arrayListOf<Int>()
        val s2 = arrayListOf<String>()
        category.children.forEach {
            s1.add(it.id)
            s2.add(it.name)
        }

        intent.putExtra("title", category.name)
        intent.putExtra("ids", s1)
        intent.putExtra("titles", s2)
        context.startActivity(intent)
    }

    fun go2search(context: Context, keyword: String? = null) {
        val intent = Intent(context, SearchActivity::class.java)
        if (!keyword.isNullOrBlank()){
            intent.putExtra("key", keyword)
        }
        context.startActivity(intent)
    }

    fun go2collect(context: Context){
        context.startActivity(Intent(context, UserActivity::class.java).apply { putExtra("type", 0) })
    }

    fun go2about(context: Context){
        context.startActivity(Intent(context, UserActivity::class.java).apply { putExtra("type", 1) })
    }

    fun go2todo(context: Context){
        context.startActivity(Intent(context, UserActivity::class.java).apply { putExtra("type", 2) })
    }

    fun go2question(context: Context) {
        context.startActivity(Intent(context, QuestionActivity::class.java))
    }

    fun go2test(context: Context) {
        context.startActivity(Intent(context, TestActivity::class.java))
    }
}