package com.wzq.jetpack.ui.adapter

import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.wzq.jetpack.App
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ItemProjectBinding
import com.wzq.jetpack.model.Article
import com.wzq.jetpack.ui.TestActivity
import com.wzq.jetpack.ui.activity.MainActivity


/**
 * Created by wzq on 2019-07-12
 *
 */
class ProjectAdapter : PagedListAdapter<Article, ProjectAdapter.ViewHolder>(ProjectDiffCallback()), ListPreloader.PreloadModelProvider<Article>  {

    override fun getPreloadItems(position: Int): List<Article> {
        return listOf(getItem(position)!!)
    }

    override fun getPreloadRequestBuilder(item: Article): RequestBuilder<*>? {
        return Glide.with(App.context).load(item.envelopePic)
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
//                Router.go2web(binding.root.context, it.tag as String)
                val intent = Intent(it.context, TestActivity::class.java)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    it.context as MainActivity,
                    Pair.create(it, it.context.resources.getString(R.string.transition_image)),
                    Pair.create(it, it.context.resources.getString(R.string.transition_image))
                )
                it.context.startActivity(intent, options.toBundle())
            }
        }
    }
}


private class ProjectDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        println(oldItem.title == newItem.title)
        return oldItem.title == newItem.title
    }

}