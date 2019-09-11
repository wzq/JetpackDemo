package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.databinding.ItemHomeBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.Router
import com.wzq.jetpack.util.thread.IOScope
import kotlinx.coroutines.launch


/**
 * Created by wzq on 2019-07-12
 *
 */
class ArticleAdapter: ListAdapter<Article, ArticleAdapter.ViewHolder>(ArticleDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
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
                        IOScope().launch {
                            repo.collectArticle(id).toString()
                        }
                    }
                }

                override fun unLiked(likeButton: LikeButton?) {
                    val id = likeButton?.tag
                    if (id is Int) {
                        IOScope().launch {
                            repo.collectCancel(id).toString()
                        }
                    }
                }

            })
            binding.root.setOnClickListener { Router.go2web(binding.root.context, it.tag as String) }
        }
    }
}


private class ArticleDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}