package com.wzq.sample.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.data.paging.CategoryPagerSource
import com.wzq.sample.databinding.FragmentCategoryListBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.ui.main.home.HomeAdapter
import com.wzq.sample.util.PAGE_SIZE
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * create by wzq on 2021/12/16
 *
 */
class CategoryListFragment : BaseFragment(), HomeAdapter.ItemClickListener {

    companion object {
        fun newInstance(id: Int): CategoryListFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = CategoryListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cid = arguments?.getInt("id")
        if (cid == null) {
            findNavController().navigateUp()
            return null
        }

        val binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        val adapter = HomeAdapter(this)
        binding.listview.adapter = adapter

        lifecycleScope.launch {
            Pager(
                PagingConfig(PAGE_SIZE, initialLoadSize = PAGE_SIZE),
                0,
            ) {
                CategoryPagerSource(cid)
            }.flow.collectLatest {
                adapter.submitData(it)
            }
        }
        return binding.root
    }

    override fun onItemClick(url: String) {
    }

    override fun onLikeClick(id: Int) {
    }

}