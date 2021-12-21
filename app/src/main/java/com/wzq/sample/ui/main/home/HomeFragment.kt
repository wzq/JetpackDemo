package com.wzq.sample.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentHomeBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.ui.main.MainFragmentDirections
import com.wzq.sample.weidget.SimpleDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment(), HomeAdapter.ItemClickListener {

    private val viewModel by viewModels<HomeViewModel>()

    private val bannerAdapter = BannerAdapter()

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

        binding.bannerView.adapter = bannerAdapter
        
        lifecycleScope.launchWhenStarted {

            val banners = viewModel.banner().getOrNull()
            bannerAdapter.submitData(banners?.data)

           viewModel.articleList.flow.collectLatest {
               listAdapter.submitData(it)
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