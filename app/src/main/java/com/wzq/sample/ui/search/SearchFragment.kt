package com.wzq.sample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    override fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        viewModel.result.onEach {
            println(it)
        }.launchIn(lifecycleScope)

        return binding.root
    }

}