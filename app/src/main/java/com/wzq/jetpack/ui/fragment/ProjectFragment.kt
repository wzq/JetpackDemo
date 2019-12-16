package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.wzq.jetpack.data.remote.NetworkState
import com.wzq.jetpack.databinding.FragmentProjectBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.ui.adapter.ProjectAdapter
import com.wzq.jetpack.viewmodel.ProjectViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectFragment : BaseFragment() {

    private val viewModel by viewModels<ProjectViewModel> { ViewModelFactory() }

    lateinit var binding: FragmentProjectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        initAdapter()
        refresh()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = ProjectAdapter(this)
        binding.projectList.adapter = adapter
        val sizeProvider = ViewPreloadSizeProvider<Article>()
        val viewPreloader = RecyclerViewPreloader(this, adapter, sizeProvider, 4)
        binding.projectList.addOnScrollListener(viewPreloader)

        viewModel.listData.observe(viewLifecycleOwner, Observer {
            println(it.toString())
            adapter.submitList(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
        })
    }

    private fun refresh() {
        viewModel.refreshState.observe(viewLifecycleOwner, Observer{
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