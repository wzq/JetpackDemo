package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentHomeBinding
import com.wzq.jetpack.ui.adapter.HomeAdapter
import com.wzq.jetpack.ui.adapter.HomePageAdapter
import com.wzq.jetpack.util.dp2px
import com.wzq.jetpack.util.threadLog
import com.wzq.jetpack.viewmodel.HomeViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory() }

    private val adapter by lazy{ HomeAdapter() }

    private val pagerAdapter by lazy { HomePageAdapter() }

    private var currentPage = 0

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeSwipe.setOnRefreshListener {
            refresh(binding)
        }
        initPager()
        initList()
        refresh(binding) //初始化页面
        return binding.root
    }

    private fun initList() {
        binding.homeList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.homeList.adapter = adapter
    }

    private fun initPager() {
        binding.homePage.adapter = pagerAdapter
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorChanged(position)
            }
        })
        viewModel.looper.observe(this, Observer {
            binding.homePage.currentItem = it
        })
    }

    private fun indicatorChanged(position: Int) {
        if (binding.homePageIndicator.childCount > position) {
            val temp = binding.homePageIndicator.getChildAt(position)
            (temp as? RadioButton)?.isChecked = true
        }
    }

    private fun buildIndicator(len: Int){
        if (len == pagerAdapter.itemCount){
            return
        }
        binding.homePageIndicator.removeAllViews()
        (0 until len).forEach { _ ->
            val rb = layoutInflater.inflate(R.layout.view_radiobutton, binding.homePageIndicator, false).also {
                val params = it.layoutParams as LinearLayout.LayoutParams
                params.marginStart = dp2px(activity, 6)
                it.layoutParams = params
            }
            binding.homePageIndicator.addView(rb)
        }
    }

    private fun refresh(b: FragmentHomeBinding) {
        b.homeSwipe.isRefreshing = true
        viewModel.getArticles(currentPage).observe(this, Observer {
            b.homeSwipe.isRefreshing = false
            adapter.submitList(it)
        })

        viewModel.banners.observe(this, Observer {
            buildIndicator(it.size)
            pagerAdapter.submitList(it)
        })
    }

    override fun back2top(){
        binding.homeScroll.fullScroll(NestedScrollView.FOCUS_UP)
    }


}