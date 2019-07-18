package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.wzq.jetpack.R
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.databinding.FragmentProjectBinding
import com.wzq.jetpack.ui.adapter.HomeAdapter
import com.wzq.jetpack.ui.adapter.ProjectAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.viewmodel.HomeViewModel


/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectFragment : BaseFragment() {

    val viewModel by lazy{ viewModel(HomeViewModel::class.java) }

    val adapter by lazy{ ProjectAdapter() }

    var currentPage = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentProjectBinding.inflate(inflater, container, false)

        binding.projectList.adapter = adapter
        binding.projectSwipe.setOnRefreshListener {
            refresh(currentPage, binding)
        }

        refresh(currentPage, binding)
        return binding.root
    }

    private fun refresh(p: Int, b: FragmentProjectBinding) {
        b.projectSwipe.isRefreshing = true
        viewModel.getLastProjects(p).observe(this, Observer {
            b.projectSwipe.isRefreshing = false
            adapter.submitList(it)
        })
    }


}