package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import com.wzq.jetpack.R
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.data.local.AppDatabase
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.ui.adapter.HomeAdapter
import com.wzq.jetpack.ui.adapter.HomePageAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.viewmodel.HomeViewModel


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeFragment : BaseFragment() {

    val viewModel by lazy{ viewModel(HomeViewModel::class.java) }

    val adapter by lazy{ HomeAdapter() }

    val pagerAdapter by lazy { HomePageAdapter() }

    var currentPage = 0

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homePage.adapter = pagerAdapter

        binding.homeList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.homeList.adapter = adapter

        binding.homeSwipe.setOnRefreshListener {
            refresh(currentPage, binding)
        }

        refresh(currentPage, binding)
        return binding.root
    }

    private fun refresh(p: Int, b: FragmentHomeBinding) {
        b.homeSwipe.isRefreshing = true
        viewModel.getArticles(p).observe(this, Observer {
            b.homeSwipe.isRefreshing = false
            adapter.submitList(it)
        })

        viewModel.banners.observe(this, Observer {
            pagerAdapter.submitList(it)
        })
    }

    override fun back2top(){
        binding.homeScroll.fullScroll(NestedScrollView.FOCUS_UP)
    }


}