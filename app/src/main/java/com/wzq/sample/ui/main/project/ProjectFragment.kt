package com.wzq.sample.ui.main.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wzq.sample.databinding.FragmentProjectBinding
import com.wzq.sample.ui.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProjectFragment : BaseFragment() {

    private val viewModel by viewModels<ProjectViewModel>()

    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectBinding.inflate(inflater, container, false)

        val adapter = ProjectAdapter()
        binding.listView.adapter = adapter

        viewModel.articleList.flow.onEach {
            adapter.submitData(it)
        }.launchIn(lifecycleScope)

        return binding.root
    }
}