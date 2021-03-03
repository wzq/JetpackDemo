package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import com.wzq.jetpack.model.Banner
import com.wzq.jetpack.util.ext.dp2px

/**
 * create by wzq on 2020/7/31
 *
 */
class BannerAdapter(val data: List<Banner> = mutableListOf()) : RecyclerView.Adapter<BannerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return Holder(root)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder) {
        buildIndicator(data.size)
    }

    class Holder(root: View) : RecyclerView.ViewHolder(root) {

        private val pagerAdapter by lazy {
            HomePageAdapter()
        }

        val pager = root.findViewById<ViewPager2>(R.id.home_pager)
        val indicator = root.findViewById<ViewGroup>(R.id.home_page_indicator)

        init {
            pager.adapter = pagerAdapter
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    indicatorChanged(position)
                }
            })
        }

        fun indicatorChanged(position: Int) {
            val temp = indicator.getChildAt(position % pagerAdapter.realItemCount())
            (temp as? RadioButton)?.isChecked = true
        }

        fun buildIndicator(len: Int) {
            if (len == pagerAdapter.realItemCount()) {
                return
            }
            indicator.removeAllViews()
            val layoutInflater = LayoutInflater.from(itemView.context)
            (0 until len).forEach { _ ->
                val rb = layoutInflater.inflate(R.layout.view_radiobutton, indicator, false).also {
                    val params = it.layoutParams as LinearLayout.LayoutParams
                    params.marginStart = dp2px(6)
                    it.layoutParams = params
                }
                indicator.addView(rb)
            }
        }
    }
}
