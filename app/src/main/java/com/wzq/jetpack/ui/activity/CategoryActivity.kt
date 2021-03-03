package com.wzq.jetpack.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.fragment.CategoryDetailFragment
import timber.log.Timber

/**
 * Created by wzq on 2019-07-24
 *
 */
class CategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        title = intent.getStringExtra("title")
        val ids = intent.getIntegerArrayListExtra("ids") ?: return
        val titles = intent.getStringArrayListExtra("titles") ?: return

        Timber.i("$ids ---- $titles")

        val pager = findViewById<ViewPager2>(R.id.category_pager)
        val tab = findViewById<TabLayout>(R.id.category_tab)
        pager.adapter = PagerAdapter(this, ids)
        pager.offscreenPageLimit = 4
        TabLayoutMediator(tab, pager) { it, position ->
            it.text = titles[position]
        }.attach()
    }

    class PagerAdapter(
        val activity: CategoryActivity,
        val data: List<Int>
    ) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = data.size

        override fun createFragment(position: Int): Fragment {
            return CategoryDetailFragment.instance(data[position])
        }
    }
}
