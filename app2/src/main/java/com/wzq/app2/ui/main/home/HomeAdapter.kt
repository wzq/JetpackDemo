package com.wzq.app2.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.app2.data.model.Article
import com.wzq.app2.data.model.ArticleDiff
import com.wzq.app2.databinding.ItemArticleBinding
import com.wzq.app2.util.timeFormat

/**
 * create by wzq on 2021/4/6
 *
 */
class HomeAdapter(private val itemClickListener: ItemClickListener) :
    PagingDataAdapter<Article, HomeAdapter.Holder>(ArticleDiff()) {

    class Holder(
        private val binding: ItemArticleBinding,
        private val itemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val url = it.tag as? String ?: return@setOnClickListener
                itemClickListener.onItemClick(url)
            }
            binding.itemHomeStar.setOnClickListener {
                val id = it.tag as? Int ?: return@setOnClickListener
                it.isSelected = !it.isSelected
                itemClickListener.onLikeClick(id)
            }
        }

        fun bindTo(data: Article) {
            binding.itemHomeTitle.text = data.title
            binding.itemHomeAuthor.text = data.author
            binding.itemHomeTag.text = data.chapterName
            binding.itemHomeTime.text = timeFormat(data.publishTime)
            binding.itemHomeStar.isSelected = data.collect
            binding.root.tag = data.link
            binding.itemHomeStar.tag = data.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val article = getItem(position) ?: return
        holder.bindTo(article)
    }

    interface ItemClickListener {
        fun onItemClick(url: String)

        fun onLikeClick(id: Int)
    }
}

