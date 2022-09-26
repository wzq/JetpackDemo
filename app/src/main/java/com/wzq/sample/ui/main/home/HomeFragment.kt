package com.wzq.sample.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.wzq.sample.NavMainDirections
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentHomeBinding
import com.wzq.sample.ui.LifecycleFragment
import com.wzq.sample.weidget.SimpleDecoration
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : LifecycleFragment(), HomeAdapter.ItemClickListener {

    private val viewModel by viewModels<HomeViewModel>()

    private val bannerAdapter = BannerAdapter()
    private val listAdapter = HomeAdapter(this)

    override fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.listView.addItemDecoration(SimpleDecoration())
        binding.listView.adapter = listAdapter
        viewModel.articleList.flow.flowWithLifecycle(lifecycle).onEach {
            listAdapter.submitData(it)
        }.launchIn(lifecycleScope)

        binding.bannerView.adapter = bannerAdapter
        viewModel.banners.flowWithLifecycle(lifecycle).onEach {
            bannerAdapter.submitData(it)
        }.launchIn(lifecycleScope)

        viewModel.banner()
        listAdapter.refresh()
        return binding.root
    }

    override fun onItemClick(url: String) {
        val directions = NavMainDirections.actionToWebFragment(url)
        findNavController().navigate(directions, navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }

            launchSingleTop = true
            restoreState = true
//            popUpTo(findNavController().graph.findStartDestination().id) {
//                saveState = true
//            }
        })
    }

    override fun onLikeClick(id: Int) {
    }
}