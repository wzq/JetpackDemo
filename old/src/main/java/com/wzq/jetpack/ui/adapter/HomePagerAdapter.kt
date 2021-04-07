package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.databinding.PagerHomeBinding
import com.wzq.jetpack.model.Banner
import com.wzq.jetpack.util.Router

/**
 * Created by wzq on 2019-07-12
 *
 */
class HomePageAdapter : ListAdapter<Banner, HomePageAdapter.ViewHolder>(DiffCallback()) {

    override fun getItemCount(): Int {
        return if (super.getItemCount() > 0) Int.MAX_VALUE else 0
    }

    override fun getItem(position: Int): Banner {
        val p = if (super.getItemCount() > 0) position % super.getItemCount() else position
        return super.getItem(p)
    }

    fun realItemCount(): Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.banner = item
        holder.binding.root.tag = item.url
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(val binding: PagerHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Router.go2web(it.context, it.tag as String)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.imagePath == newItem.imagePath
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }
}
