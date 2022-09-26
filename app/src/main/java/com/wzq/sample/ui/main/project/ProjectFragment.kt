package com.wzq.sample.ui.main.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentProjectBinding
import com.wzq.sample.util.PAGE_SIZE
import kotlinx.coroutines.flow.collect

class ProjectFragment : Fragment(R.layout.fragment_project) {

    private val viewModel by viewModels<ProjectViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentProjectBinding.bind(view)

        val adapter = ProjectAdapter()
        binding.listView.adapter = adapter

        lifecycleScope.launchWhenStarted {
//           viewModel.articleList.flow.collect {
//                adapter.submitData(it)
//            }
        }
    }
}