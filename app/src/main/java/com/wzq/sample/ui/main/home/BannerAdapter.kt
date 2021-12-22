package com.wzq.sample.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wzq.sample.R
import com.wzq.sample.data.model.Banner

/**
 * create by wzq on 2021/12/16
 *
 */
class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerHolder>() {

    val data = mutableListOf<Banner>()

    fun submitData(list: List<Banner>?) {
        data.clear()
        if (!list.isNullOrEmpty()) {
            data.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val root = LayoutInflater.from(parent.context).inflate(
            R.layout.item_banner,
            parent,
            false
        )
        return BannerHolder(root)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        if (data.isNullOrEmpty()) return
        val item = data[position % data.size]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    class BannerHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val content: ImageView = view.findViewById(R.id.content)
        fun bind(banner: Banner) {
            content.load(banner.imagePath)
        }
    }
}

