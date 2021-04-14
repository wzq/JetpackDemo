package com.wzq.sample.ui.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wzq.sample.R
import com.wzq.sample.data.remote.Linker
import com.wzq.sample.databinding.FragmentCategoryBinding
import com.wzq.sample.weidget.SimpleDecoration

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCategoryBinding.bind(view)
        val adapter = CategoryAdapter()
        binding.listView.adapter = adapter
        binding.listView.addItemDecoration(SimpleDecoration())
        lifecycleScope.launchWhenStarted {
//            try {
//                val data = Linker.mainApi.getCategory().getOrNull()
//                adapter.submitList(data)
//            }catch(ex: Exception){
//                ex.printStackTrace()
//            }
        }
    }
}