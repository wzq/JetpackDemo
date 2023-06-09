package com.wzq.sample.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.data.model.HotKeywordsItem
import com.wzq.sample.databinding.ItemHotWordsBinding

class SuggestionAdapter : ListAdapter<HotKeywordsItem, SuggestionAdapter.Holder>(HotKeywordsItem.Diff()) {
    class Holder(
        private val binding: ItemHotWordsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                // TODO:
            }
        }

        fun bindTo(data: HotKeywordsItem) {
            binding.name.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemHotWordsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindTo(getItem(position))
    }
}