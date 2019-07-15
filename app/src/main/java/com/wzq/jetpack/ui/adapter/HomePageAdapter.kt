package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.databinding.ItemHomeBinding
import com.wzq.jetpack.databinding.ItemHomePagerBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.model.Banner
import com.wzq.jetpack.util.Router


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomePageAdapter(val data: List<Banner>): RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.banner = item
        holder.binding.root.tag = item.url
        holder.binding.executePendingBindings()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomePagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class ViewHolder(val binding: ItemHomePagerBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Router.go2web(it.context, it.tag as String)
            }
        }
    }
}


