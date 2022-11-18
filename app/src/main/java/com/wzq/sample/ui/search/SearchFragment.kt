package com.wzq.sample.ui.search

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.databinding.FragmentSearchBinding
import com.wzq.sample.ui.BaseFragment

/**
 * create by wzq on 2022/10/9
 *
 */
class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    private val args by navArgs<SearchFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        binding.appbar.setStatusBarForegroundColor(Color.WHITE)

        val adapter = Adapter()
        binding.listview.adapter = adapter

        val list = mutableListOf<String>()
        repeat(1000) {
            list.add("$it")
        }
        adapter.submitList(list)

        return binding.root
    }

    class Adapter : ListAdapter<String, RecyclerView.ViewHolder>(Differ()) {
        class Differ : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val root = TextView(parent.context)
            root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return object : RecyclerView.ViewHolder(root) {}
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder.itemView as TextView).text = getItem(position)
        }


    }

}