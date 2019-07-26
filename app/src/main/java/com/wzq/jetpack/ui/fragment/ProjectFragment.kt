package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.wzq.jetpack.databinding.FragmentProjectBinding
import com.wzq.jetpack.data.remote.NetworkState
import com.wzq.jetpack.ui.adapter.ProjectAdapter
import com.wzq.jetpack.viewmodel.ProjectViewModel


/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectFragment : BaseFragment() {

    val viewModel by lazy{ viewModel(ProjectViewModel::class.java) }


    lateinit var binding: FragmentProjectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        initAdapter()
        refresh()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = ProjectAdapter()
        binding.projectList.adapter = adapter
        viewModel.listData.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkState.observe(this, Observer {
        })
    }

    private fun refresh() {
        viewModel.refreshState.observe(this, Observer{
            binding.projectSwipe.isRefreshing = it == NetworkState.LOADING
        })
        binding.projectSwipe.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun back2top(){
        binding.projectList.scrollToPosition(0)
    }
}