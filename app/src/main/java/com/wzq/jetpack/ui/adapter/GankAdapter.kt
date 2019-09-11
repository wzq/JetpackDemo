package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.databinding.ItemGankBinding
import com.wzq.jetpack.databinding.ItemHomeBinding
import com.wzq.jetpack.model.result.GankItem
import com.wzq.jetpack.util.Router


/**
 * Created by wzq on 2019-07-12
 *
 */
class GankAdapter : ListAdapter<GankItem, GankAdapter.ViewHolder>(GankDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.data = item
        holder.binding.root.tag =  item.url
        holder.binding.executePendingBindings()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class ViewHolder(val binding: ItemGankBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { Router.go2web(binding.root.context, binding.root.tag.toString()) }
        }
    }
}


private class GankDiffCallback : DiffUtil.ItemCallback<GankItem>() {
    override fun areItemsTheSame(oldItem: GankItem, newItem: GankItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GankItem, newItem: GankItem): Boolean {
        return oldItem.desc == newItem.desc
    }
}