package com.wzq.jetpack.test.video

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.wzq.jetpack.R
import kotlinx.android.synthetic.main.fragment_video_viewpager.*


/**
 * create by wzq on 2020/11/6
 *
 */
class VideoWithViewPager2Page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerIndex = view.findViewById<TextView>(R.id.pager_index)
        val pagerCallback = object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                val total = pager.adapter?.itemCount ?: 0
                if (total > 0) pagerIndex.text = "${position + 1}/$total"
            }
        }
        pager.registerOnPageChangeCallback(pagerCallback)
        pager.adapter = PagerAdapter()
    }

    override fun onDestroyView() {
        pager.adapter = null
        super.onDestroyView()
    }
}

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.VideoHolder>() {
    class VideoHolder(val root: CustomVideoView) : RecyclerView.ViewHolder(root)

    val data = mutableListOf(
        "http://mgcdn.vod.migucloud.com/vi1/198.3lYBKuRh5spTAvWGhPZj.56.r48jfj.mp4",
        "http://mgcdn.vod.migucloud.com/vi1/198.3lYBKuRh5spTAvWGhPZj.56.r48jfj.mp4",
        "http://mgcdn.vod.migucloud.com/vi1/198.3lYBKuRh5spTAvWGhPZj.56.r48jfj.mp4",
        "http://mgcdn.vod.migucloud.com/vi1/198.3lYBKuRh5spTAvWGhPZj.56.r48jfj.mp4",
        "http://mgcdn.vod.migucloud.com/vi1/198.3lYBKuRh5spTAvWGhPZj.56.r48jfj.mp4"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val root = CustomVideoView(parent.context)
        return VideoHolder(root)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.root.setUrl(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewAttachedToWindow(holder: VideoHolder) {
        super.onViewAttachedToWindow(holder)
        holder.root.isActivated = true
    }

    override fun onViewDetachedFromWindow(holder: VideoHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.root.isActivated = false
    }

    override fun onViewRecycled(holder: VideoHolder) {
        super.onViewRecycled(holder)
        holder.root.releasePlayer()
    }
}

