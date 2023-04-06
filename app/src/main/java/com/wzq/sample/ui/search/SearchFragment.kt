package com.wzq.sample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wzq.sample.databinding.FragmentSearchBinding
import com.wzq.sample.ui.LifecycleFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * create by wzq on 2022/10/9
 *
 */
class SearchFragment : LifecycleFragment() {
    private lateinit var binding: FragmentSearchBinding

    private val args by navArgs<SearchFragmentArgs>()

    private val viewModel: SearchViewModel by viewModels()

    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.inputKeywords.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(v.text.toString())
            }
            false
        }

        val adapter = SearchAdapter()
        binding.listview.adapter = adapter

        viewModel.result.onEach {
            adapter.submitList(it?.datas)
        }.launchIn(lifecycleScope)

        return binding.root
    }
}