package com.wzq.sample.ui.main.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.model.ArticleDiff
import com.wzq.sample.databinding.ItemProjectBinding
import com.wzq.sample.util.timeFormat

class ProjectAdapter : PagingDataAdapter<Article, ProjectAdapter.ViewHolder>(ArticleDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Article?) {
            item ?: return
            binding.itemProjectPic.load(item.envelopePic)
            binding.itemProjectAuthor.text = item.author
            binding.itemProjectTitle.text =
                HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.itemProjectTime.text = timeFormat(item.publishTime)
        }
    }
}