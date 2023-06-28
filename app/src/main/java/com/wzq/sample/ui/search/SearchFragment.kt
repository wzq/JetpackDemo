package com.wzq.sample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wzq.sample.databinding.FragmentSearchBinding
import com.wzq.sample.ui.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * create by wzq on 2022/10/9
 *
 */
class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding

//    private val args by navArgs<SearchFragmentArgs>()

    private val viewModel: SearchViewModel by viewModels()

    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.searchView.editText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearch(v.text.toString())
                true
            } else false
        }

//        binding.searchView.addTransitionListener { searchView, previousState, newState ->
//            println(newState)
//        }

        binding.searchView.setOnMenuItemClickListener {
            true
        }

        val adapter = SearchAdapter()
        binding.listview.adapter = adapter

        viewModel.result.onEach {
            adapter.submitList(it?.datas)
        }.launchIn(lifecycleScope)

        val suggestionAdapter = SuggestionAdapter {
            onSearch(it)
        }
        binding.searchSuggest.adapter = suggestionAdapter

        viewModel.hotWords.onEach {
            suggestionAdapter.submitList(it)
        }.launchIn(lifecycleScope)

        return binding.root
    }

    private fun onSearch(kw: String) {
        viewModel.search(kw)
        binding.searchBar.text = kw
        binding.searchView.hide()
    }
}