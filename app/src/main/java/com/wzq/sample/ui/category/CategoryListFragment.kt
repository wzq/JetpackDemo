package com.wzq.sample.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.R
import com.wzq.sample.data.paging.CategoryPagerSource
import com.wzq.sample.databinding.FragmentCategoryListBinding
import com.wzq.sample.ui.main.home.HomeAdapter
import com.wzq.sample.util.PAGE_SIZE
import kotlinx.coroutines.flow.collectLatest

/**
 * create by wzq on 2021/12/16
 *
 */
class CategoryListFragment: Fragment(R.layout.fragment_category_list), HomeAdapter.ItemClickListener {

    companion object {
        fun newInstance(id: Int): CategoryListFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = CategoryListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCategoryListBinding.bind(view)
        val cid = arguments?.getInt("id") ?: return

        val adapter = HomeAdapter(this)
        binding.listview.adapter = adapter

        lifecycleScope.launchWhenStarted {
            Pager(
                PagingConfig(PAGE_SIZE),
                0,
            ) {
                CategoryPagerSource(cid)
            }.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onItemClick(url: String) {
    }

    override fun onLikeClick(id: Int) {
    }

}