package com.wzq.sample.data.model

import androidx.recyclerview.widget.DiffUtil

data class HotKeywordsItem(
    val id: Int?,
    val link: String?,
    val name: String?,
    val order: Int?,
    val visible: Int?
) {

    class Diff : DiffUtil.ItemCallback<HotKeywordsItem>() {
        override fun areItemsTheSame(oldItem: HotKeywordsItem, newItem: HotKeywordsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HotKeywordsItem,
            newItem: HotKeywordsItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }
}