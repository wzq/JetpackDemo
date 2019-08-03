package com.wzq.jetpack.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.fragment.CategorySecFragment


/**
 * Created by wzq on 2019-07-24
 *
 */
class CategoryActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        title = intent.getStringExtra("title")

        val len = intent.getIntArrayExtra("ids")

        val pager = findViewById<ViewPager2>(R.id.category_pager)
        pager.adapter = PagerAdapter(len, this)
    }


    class PagerAdapter(val items: IntArray, activity: BaseActivity): FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return items.size
        }

        override fun createFragment(position: Int): Fragment {
            return CategorySecFragment.instance(position)
        }

    }
}