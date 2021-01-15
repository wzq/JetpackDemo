package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.wzq.jetpack.databinding.FragmentProjectBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.test.transition.util.SpringAddItemAnimator
import com.wzq.jetpack.ui.adapter.ProjectAdapter
import com.wzq.jetpack.viewmodel.ProjectViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectFragment : BaseFragment() {

    private val viewModel by viewModels<ProjectViewModel> { ViewModelFactory() }

    val adapter by lazy { ProjectAdapter(this) }

    lateinit var binding: FragmentProjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        initAdapter()
        binding.projectSwipe.setOnRefreshListener {
            adapter.refresh()
        }
        return binding.root
    }

    private fun initAdapter() {
        binding.projectList.setHasFixedSize(true)
        binding.projectList.itemAnimator = SpringAddItemAnimator()
        binding.projectList.adapter = adapter
        val sizeProvider = ViewPreloadSizeProvider<Article>()
        val viewPreloader = RecyclerViewPreloader(this, adapter, sizeProvider, 4)
        binding.projectList.addOnScrollListener(viewPreloader)

        adapter.addLoadStateListener {
            binding.projectSwipe.isRefreshing = it.refresh is LoadState.Loading

            when(it.append) {
                LoadState.Loading -> {
                    println("Loading ")
                }
                is LoadState.NotLoading ->  println("NotLoading ")
                is LoadState.Error ->  println("Error ")
            }

            when(it.refresh) {
                LoadState.Loading -> {
                    println("refresh -------Loading ")
                }
                is LoadState.NotLoading ->  println("refresh -------NotLoading ")
                is LoadState.Error ->  println("refresh -------Error ")
            }
        }

        viewModel.fetchLastProject().observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }

}