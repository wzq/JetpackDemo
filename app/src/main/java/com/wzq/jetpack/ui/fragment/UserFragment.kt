package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.map
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentUserBinding
import com.wzq.jetpack.ui.adapter.PageArticleAdapter
import com.wzq.jetpack.util.GlideApp
import com.wzq.jetpack.util.monitor.LoginMonitor
import com.wzq.jetpack.viewmodel.UserViewModel

class UserFragment : BaseFragment(R.layout.fragment_user) {

    private val loginMonitor = LoginMonitor()

    private val viewModel by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentUserBinding.bind(view)

        val defHeadIcon = "http://193.149.161.209/static/images/p001.png"
        GlideApp.with(this).load(defHeadIcon).into(binding.userHead)

        loginMonitor.observe(viewLifecycleOwner) {
            binding.userName.text = it
        }
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