package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.databinding.ItemHomeBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.Router
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by wzq on 2019-07-12
 *
 */
class HomeAdapter: PagedListAdapter<Article, HomeAdapter.ViewHolder>(HomeDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position) ?: return
        holder.binding.itemHomeStar.isLiked = article.collect
        holder.binding.article = article
        holder.binding.root.tag = article.link
        holder.binding.itemHomeStar.tag = article.id
        holder.binding.executePendingBindings()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class ViewHolder(val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.itemHomeStar.setOnLikeListener(object : OnLikeListener{

                val repo = BaseRepo()

                override fun liked(likeButton: LikeButton?) {
                    val id = likeButton?.tag
                    if (id is Int) {
                        GlobalScope.launch {
                            repo.collectArticle(id).toString()
                        }
                    }
                }

                override fun unLiked(likeButton: LikeButton?) {
                    val id = likeButton?.tag
                    if (id is Int) {
                        GlobalScope.launch {
                            repo.collectCancel(id).toString()
                        }
                    }
                }

            })
            binding.root.setOnClickListener { Router.go2web(binding.root.context, it.tag as String) }
        }
    }
}


private class HomeDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

}