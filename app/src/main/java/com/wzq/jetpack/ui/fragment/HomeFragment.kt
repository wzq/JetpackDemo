package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.wzq.jetpack.R
import com.wzq.jetpack.data.HomeRepo
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

    val viewModel by lazy{ createViewModel(HomeRepo(), (HomeViewModel::class.java)) }

    val adapter by lazy{ HomeAdapter() }

//    val pagerAdapter by lazy { HomePageAdapter() }

    var currentPage = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

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
            b.homePage.adapter = HomePageAdapter(it)
        })
    }


}