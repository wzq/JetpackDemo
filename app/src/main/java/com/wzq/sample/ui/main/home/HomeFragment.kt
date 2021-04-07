package com.wzq.sample.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentHomeBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.ui.main.MainFragmentDirections
import com.wzq.sample.weidget.SimpleDecoration
import kotlinx.coroutines.flow.collect

class HomeFragment : BaseFragment(), HomeAdapter.ItemClickListener {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val listAdapter = HomeAdapter(this)
        binding.listView.addItemDecoration(SimpleDecoration())
        binding.listView.adapter = listAdapter
        lifecycleScope.launchWhenStarted {
            viewModel.articleList.flow.collect {
                listAdapter.submitData(requireActivity().lifecycle, it)
            }
        }
    }

    override fun onItemClick(url: String) {
        val directions = MainFragmentDirections.actionMainFragmentToWebActivity(url)
        findNavController().navigate(directions)
    }

    override fun onLikeClick(id: Int) {
    }
}