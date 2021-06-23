package com.wzq.sample.ui.main.category

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.data.model.Category
import com.wzq.sample.databinding.ItemCategoryBinding
import com.wzq.sample.ui.category.CategoryActivity

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemCategoryTitle.text = item.name
        holder.binding.itemCategoryTag.text = item.fetchChildrenName()
        holder.binding.root.tag = item
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val tag = it.tag
                if (tag is Category) {
                    val intent = Intent(it.context, CategoryActivity::class.java)

                    val s1 = arrayListOf<Int>()
                    val s2 = arrayListOf<String>()
                    tag.children.forEach { c->
                        s1.add(c.id)
                        s2.add(c.name)
                    }

                    intent.putExtra("title", tag.name)
                    intent.putExtra("ids", s1)
                    intent.putExtra("titles", s2)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}

private class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }
}