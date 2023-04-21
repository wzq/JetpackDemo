package com.wzq.sample.ui.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.wzq.sample.data.MainRepo
import com.wzq.sample.databinding.FragmentCategoryBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.weidget.SimpleDecoration
import kotlinx.coroutines.launch

class CategoryFragment : BaseFragment() {

    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        val adapter = CategoryAdapter()
        binding.listView.adapter = adapter
        binding.listView.addItemDecoration(SimpleDecoration(requireContext()))
        lifecycleScope.launch {
            val repo = MainRepo()
            val data = repo.getCategory().getOrNull()
            adapter.submitList(data?.data)
        }
        return binding.root
    }
}