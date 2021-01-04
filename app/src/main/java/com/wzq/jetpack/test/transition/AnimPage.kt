package com.wzq.jetpack.test.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wzq.jetpack.databinding.FragmentTestAnimBinding

/**
 * create by wzq on 2020/11/23
 *
 */
class AnimPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTestAnimBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTestAnimBinding.bind(view)
        binding.root.setOnClickListener { }
    }
}