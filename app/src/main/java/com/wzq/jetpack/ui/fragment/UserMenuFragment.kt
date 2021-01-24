package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentUserMenuBinding
import com.wzq.jetpack.test.TestActivity
import com.wzq.jetpack.util.ext.openPage

class UserMenuFragment : BaseFragment(R.layout.fragment_user_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentUserMenuBinding.bind(view)
        binding.b1.setOnClickListener {
            findNavController().navigate(R.id.user_to_collect)
        }

        binding.b2.setOnClickListener {
            findNavController().navigate(R.id.user_to_todo)
        }

        binding.b3.setOnClickListener {
            requireActivity().openPage(TestActivity::class)
        }
    }

}