package com.wzq.jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.databinding.ItemQuestionBinding
import com.wzq.jetpack.model.Question
import com.wzq.jetpack.util.Router

class QuestionAdapter : ListAdapter<Question, QuestionAdapter.Holder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            questionTitle.text = item.title
            questionAuthor.text = item.author
            questionCategory.text = "${item.chapterName}/${item.superChapterName}"
            questionTime.text = item.niceDate
            root.setOnClickListener { Router.go2web(it.context, item.link) }
        }
    }

    class Holder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)
}

private class Diff : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.title == newItem.title
    }
}
