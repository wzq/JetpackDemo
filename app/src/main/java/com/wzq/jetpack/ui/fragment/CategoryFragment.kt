package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.wzq.jetpack.R
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.databinding.FragmentCategoryBinding
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.ui.adapter.CategoryAdapter
import com.wzq.jetpack.ui.adapter.HomeAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.viewmodel.CategoryViewModel
import com.wzq.jetpack.viewmodel.HomeViewModel
import timber.log.Timber


/**
 * Created by wzq on 2019-07-12
 *
 */
class CategoryFragment : BaseFragment() {

    val viewModel by lazy{ viewModel(CategoryViewModel::class.java) }

    val adapter by lazy{ CategoryAdapter() }

    lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding.categoryList.addItemDecoration(SimpleDecoration(context, R.color.line_gray))
        binding.categoryList.adapter = adapter
        binding.categorySwipe.setOnRefreshListener {
            refresh()
        }

        refresh()
        return binding.root
    }

    private fun refresh() {
        binding.categorySwipe.isRefreshing = true
        viewModel.categoryList.observe(this, Observer {
            binding.categorySwipe.isRefreshing = false
            Timber.i("category ==> $it.")
            adapter.submitList(it)
        })
    }


    override fun back2top(){
        binding.categoryList.scrollToPosition(0)
    }
}