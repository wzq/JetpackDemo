package com.wzq.sample.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wzq.sample.databinding.FragmentCategoryDetailBinding
import com.wzq.sample.ui.BaseFragment
import kotlinx.coroutines.launch

/**
 * create by wzq on 2022/11/16
 *
 */
class CategoryDetailFragment : BaseFragment() {

    private val args by navArgs<CategoryDetailFragmentArgs>()

    private lateinit var binding: FragmentCategoryDetailBinding

    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        binding.toolbar.title = args.title
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        val ids = args.subId
        val titles = args.subTitle
        if (ids != null && titles != null) {

            val data = mutableListOf<Int>()

            val adapter = PagerAdapter(this, data)
            binding.categoryPager.adapter = adapter
            TabLayoutMediator(binding.categoryTab, binding.categoryPager) { it, position ->
                it.text = titles[position]
            }.attach()

            lifecycleScope.launch {
//                delay(500)
                data.addAll(ids.toList())
                adapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    class PagerAdapter(
        fragment: Fragment, private val data: List<Int>
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = data.size

        override fun createFragment(position: Int): Fragment {
            return CategoryListFragment.newInstance(data[position])
        }
    }
}