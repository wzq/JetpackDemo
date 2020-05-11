package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentGankBinding
import com.wzq.jetpack.ui.adapter.GankAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.viewmodel.GankViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


class GankFragment : BaseFragment() {

    private val viewModel by viewModels<GankViewModel> { ViewModelFactory() }

    private val adapter = GankAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGankBinding.inflate(inflater, container, false)
        binding.gankList.addItemDecoration(SimpleDecoration(activity))
        binding.gankList.adapter = adapter

        binding.gankSwipe.setOnRefreshListener { viewModel.getDailyInfo() }
        binding.gankSwipe.isRefreshing = true
        viewModel.getDailyInfo()

        viewModel.dailyList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.gankSwipe.isRefreshing = false
        })
        return binding.root
    }

    override fun back2top() {
        view?.findViewById<RecyclerView>(R.id.gank_list)?.scrollToPosition(0)
    }

}