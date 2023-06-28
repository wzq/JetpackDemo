package com.wzq.sample.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.data.model.HotKeywordsItem
import com.wzq.sample.databinding.ItemHotWordsBinding

class SuggestionAdapter(private val onSearch: (String) -> Unit) :
    ListAdapter<HotKeywordsItem, SuggestionAdapter.Holder>(HotKeywordsItem.Diff()) {
    class Holder(
        private val binding: ItemHotWordsBinding,
        private val onSearch: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val keywords = it.tag as? String
                if (!keywords.isNullOrEmpty()) {
                    onSearch(keywords)
                }
            }
        }

        fun bindTo(data: HotKeywordsItem) {
            binding.name.text = data.name
            binding.root.tag = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHotWordsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onSearch
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindTo(getItem(position))
    }
}