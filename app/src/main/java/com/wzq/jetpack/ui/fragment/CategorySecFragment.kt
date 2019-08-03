package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wzq.jetpack.databinding.FragmentCategorySecBinding
import com.wzq.jetpack.viewmodel.CategoryViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-08-01
 *
 */
class CategorySecFragment : BaseFragment() {

    companion object {
        fun instance(id: Int): CategorySecFragment = CategorySecFragment().apply {
            arguments = Bundle().also { it.putInt("id", id) }
        }

    }

    val viewMoel by viewModels<CategoryViewModel> { ViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCategorySecBinding.inflate(inflater, container, false)
        val cid = arguments?.getInt("id", 0)
        binding.test.text = cid.toString()
        return binding.root
    }
}