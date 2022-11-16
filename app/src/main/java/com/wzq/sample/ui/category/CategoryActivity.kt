//package com.wzq.sample.ui.category
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.metrics.performance.JankStats
//import androidx.metrics.performance.PerformanceMetricsState
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.google.android.material.tabs.TabLayoutMediator
//import com.wzq.sample.databinding.ActivityCategoryBinding
//import com.wzq.sample.ui.BaseActivity
//import com.wzq.sample.util.systemBarMode
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.asExecutor
//
///**
// * create by wzq on 2021/5/31
// *
// */
//class CategoryActivity: BaseActivity() {
//
//    private lateinit var jankStats: JankStats
//
//    private val jankFrameListener = JankStats.OnFrameListener {
//        println(it.toString())
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        systemBarMode(false)
//        val binding = ActivityCategoryBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.toolbar.title = intent.getStringExtra("title")
//        binding.toolbar.setNavigationOnClickListener {
//            finish()
//        }
//        val ids = intent.getIntegerArrayListExtra("ids") ?: return
//        val titles = intent.getStringArrayListExtra("titles") ?: return
//
//        binding.categoryPager.also {
//            it.adapter = PagerAdapter(this, ids)
//            it.offscreenPageLimit = 4
//        }
//
//        TabLayoutMediator(binding.categoryTab, binding.categoryPager) { it, position ->
//            it.text = titles[position]
//        }.attach()
//
//        val metricsStateHolder = PerformanceMetricsState.getForHierarchy(binding.root)
//
//        jankStats = JankStats.createAndTrack(window, Dispatchers.Default.asExecutor(), jankFrameListener)
//
//        metricsStateHolder.state?.addState("Activity", javaClass.simpleName)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        jankStats.isTrackingEnabled = true
//    }
//
//    override fun onPause() {
//        super.onPause()
//        jankStats.isTrackingEnabled = false
//    }
//
//    class PagerAdapter(
//        fragment: Fragment,
//        val data: List<Int>
//    ) : FragmentStateAdapter(fragment) {
//        override fun getItemCount(): Int = data.size
//
//        override fun createFragment(position: Int): Fragment {
//            return CategoryListFragment.newInstance(data[position])
//        }
//    }
//}