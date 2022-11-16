package com.wzq.sample.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wzq.sample.databinding.FragmentCategoryDetailBinding
import com.wzq.sample.ui.LifecycleFragment

/**
 * create by wzq on 2022/11/16
 *
 */
class CategoryDetailFragment : LifecycleFragment() {

    private val args by navArgs<CategoryDetailFragmentArgs>()

    override fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        binding.toolbar.title = args.title
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val ids = args.subId
        val titles = args.subTitle

        if (ids != null && titles != null) {
            binding.categoryPager.also {
                it.adapter = PagerAdapter(this, ids)
//                it.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
            }

            TabLayoutMediator(binding.categoryTab, binding.categoryPager) { it, position ->
                it.text = titles[position]
            }.attach()
        }
        return binding.root
    }

    class PagerAdapter(
        fragment: Fragment, private val data: IntArray
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = data.size

        override fun createFragment(position: Int): Fragment {
            println(position)
            return CategoryListFragment.newInstance(data[position])
        }
    }
}