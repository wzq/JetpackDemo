package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.ui.adapter.ArticleAdapter
import com.wzq.jetpack.ui.adapter.HomePageAdapter
import com.wzq.jetpack.util.dp2px
import com.wzq.jetpack.viewmodel.HomeViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory() }

    private val pagerAdapter = HomePageAdapter()

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.homeSwipe.setOnRefreshListener {
            viewModel.refresh()
        }

        initPager()
        initList()
        return binding.root
    }

    private fun initList() {
        val adapter = ArticleAdapter()
        binding.homeList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.homeList.adapter = adapter
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            binding.homeSwipe.isRefreshing = false
            adapter.submitList(it)
        })
    }

    private fun initPager() {
        binding.homePage.adapter = pagerAdapter
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorChanged(position)
            }
        })
        @OptIn(ExperimentalCoroutinesApi::class)
        viewModel.looper.observe(viewLifecycleOwner)  {
            binding.homePage.currentItem = it
        }
        viewModel.banners.observe(viewLifecycleOwner, Observer {
            buildIndicator(it.size)
            pagerAdapter.submitList(it)
        })
    }

    private fun indicatorChanged(position: Int) {
            val temp = binding.homePageIndicator.getChildAt(position%pagerAdapter.realItemCount())
            (temp as? RadioButton)?.isChecked = true
    }

    private fun buildIndicator(len: Int){
        if (len == pagerAdapter.realItemCount()){
            return
        }
        binding.homePageIndicator.removeAllViews()
        (0 until len).forEach { _ ->
            val rb = layoutInflater.inflate(R.layout.view_radiobutton, binding.homePageIndicator, false).also {
                val params = it.layoutParams as LinearLayout.LayoutParams
                params.marginStart = dp2px(6)
                it.layoutParams = params
            }
            binding.homePageIndicator.addView(rb)
        }
    }

    override fun back2top(){
        binding.homeScroll.fullScroll(NestedScrollView.FOCUS_UP)
    }


}