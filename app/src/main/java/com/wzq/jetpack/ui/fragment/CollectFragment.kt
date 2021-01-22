package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentCollectBinding
import com.wzq.jetpack.ui.adapter.PageArticleAdapter
import com.wzq.jetpack.viewmodel.UserViewModel

class CollectFragment : BaseFragment(){

    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCollectBinding.bind(view)
        binding.userCollect.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        val adapter = PageArticleAdapter()
        binding.userCollect.adapter = adapter
        adapter.addLoadStateListener {
            binding.userSwipe.isRefreshing = it.refresh is LoadState.Loading
        }
        viewModel.collectList.observe(viewLifecycleOwner) {
            val data = it.map { article ->
                article.collect = true
                article
            }
            adapter.submitData(lifecycle, data)
        }
        binding.userSwipe.setOnRefreshListener { adapter.refresh() }
    }
}