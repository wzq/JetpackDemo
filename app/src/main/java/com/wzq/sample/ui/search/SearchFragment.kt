package com.wzq.sample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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
        println(args.keywords)
        return binding.root
    }

}