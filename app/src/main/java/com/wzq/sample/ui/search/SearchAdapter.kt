package com.wzq.sample.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.data.model.Article
import com.wzq.sample.data.model.ArticleDiff
import com.wzq.sample.databinding.ItemArticleBinding
import com.wzq.sample.ui.detail.WebFragmentDirections
import com.wzq.sample.util.jumpTo
import com.wzq.sample.util.timeFormat

class SearchAdapter : ListAdapter<Article, SearchAdapter.Holder>(ArticleDiff()) {
    class Holder(
        private val binding: ItemArticleBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val url = it.tag as? String ?: return@setOnClickListener
                it.findNavController().jumpTo(SearchFragmentDirections.actionSearchFragmentToWebFragment(url))
            }
        }

        fun bindTo(data: Article) {
            binding.itemHomeTitle.text = HtmlCompat.fromHtml(data.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.itemHomeAuthor.text = data.shareUser
            binding.itemHomeTag.text = data.chapterName
            binding.itemHomeTime.text = timeFormat(data.publishTime)
            binding.itemHomeStar.isSelected = data.collect
            binding.root.tag = data.link
            binding.itemHomeStar.tag = data.id
            println(binding.itemHomeAuthor.top)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindTo(getItem(position))
    }
}