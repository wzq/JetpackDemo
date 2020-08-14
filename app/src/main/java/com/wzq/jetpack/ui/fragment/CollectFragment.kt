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
import com.wzq.jetpack.util.toast

class CollectFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_collect, container, false) as? RecyclerView
        val adapter = ArticleAdapter()
        root?.adapter = adapter
        lifecycleScope.launchWhenCreated {
            val result = Linker.api.getCollectList(0)
            requireActivity().toast(result.errorMsg)
            result.data?.run {
                adapter.submitList(datas.also { it.forEach{ et-> et.collect = true} })
            }
        }
        return root
    }
}