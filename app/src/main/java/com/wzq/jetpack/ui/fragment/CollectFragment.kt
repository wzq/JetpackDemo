package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.Linker
import com.wzq.jetpack.ui.adapter.ArticleAdapter
import kotlinx.coroutines.launch

class CollectFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_collect, container, false) as? RecyclerView
        val adapter = ArticleAdapter()
        root?.adapter = adapter
        lifecycleScope.launch {
            val data = Linker.api.getCollectList(0)
            adapter.submitList(data.data.datas.also { it.forEach{ et-> et.collect = true} })
        }
        return root
    }
}