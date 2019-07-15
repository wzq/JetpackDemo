package com.wzq.jetpack.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.databinding.ItemHomeBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.Router


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeAdapter: ListAdapter<Article, HomeAdapter.ViewHolder>(HomeDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = getItem(position)
        holder.binding.article = article
        holder.binding.root.tag = article.link
        holder.binding.executePendingBindings()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class ViewHolder(val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { Router.go2web(binding.root.context, it.tag as String) }
        }
    }
}


private class HomeDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}