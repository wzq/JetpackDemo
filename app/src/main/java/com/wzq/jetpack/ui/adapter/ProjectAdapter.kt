package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.wzq.jetpack.databinding.ItemProjectBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.util.Router

/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectAdapter(private val host: Fragment) :
    PagingDataAdapter<Article, ProjectAdapter.ViewHolder>(ProjectDiffCallback()),
    ListPreloader.PreloadModelProvider<Article> {

    override fun getPreloadItems(position: Int): List<Article> {
        return listOf(getItem(position)!!)
    }

    override fun getPreloadRequestBuilder(item: Article): RequestBuilder<*>? {
        return Glide.with(host).load(item.envelopePic)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = getItem(position)
        holder.binding.article = article
        holder.binding.root.tag = article?.link
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Router.go2web(binding.root.context, it.tag as String)
            }
        }
    }
}

private class ProjectDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }
}
