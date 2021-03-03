package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.ui.adapter.HomePageAdapter
import com.wzq.jetpack.ui.adapter.PageArticleAdapter
import com.wzq.jetpack.util.ext.dp2px
import com.wzq.jetpack.viewmodel.HomeViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory() }

    private val pagerAdapter = HomePageAdapter()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)

        initPager()
        initList()

        if (savedInstanceState == null) {
            viewModel.articleList.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
        }
    }
    val adapter = PageArticleAdapter()

    private fun initList() {
        adapter.addLoadStateListener {
            binding.homeSwipe.isRefreshing = it.refresh is LoadState.Loading
        }
        binding.homeSwipe.setOnRefreshListener {
            adapter.refresh()
        }
        binding.homeList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.homeList.adapter = adapter
    }

    private fun initPager() {
        binding.homePage.adapter = pagerAdapter
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorChanged(position)
            }
        })
        viewModel.looper.observe(
            viewLifecycleOwner,
            {
                binding.homePage.currentItem = it
            }
        )
        viewModel.banners.observe(viewLifecycleOwner) {
            buildIndicator(it.size)
            pagerAdapter.submitList(it)
        }
    }

    private fun indicatorChanged(position: Int) {
        val temp = binding.homePageIndicator.getChildAt(position % pagerAdapter.realItemCount())
        (temp as? RadioButton)?.isChecked = true
    }

    private fun buildIndicator(len: Int) {
        if (len == pagerAdapter.realItemCount()) {
            return
        }
        binding.homePageIndicator.removeAllViews()
        (0 until len).forEach { _ ->
            val rb =
                layoutInflater.inflate(R.layout.view_radiobutton, binding.homePageIndicator, false)
                    .also {
                        val params = it.layoutParams as LinearLayout.LayoutParams
                        params.marginStart = dp2px(6)
                        it.layoutParams = params
                    }
            binding.homePageIndicator.addView(rb)
        }
    }
}
