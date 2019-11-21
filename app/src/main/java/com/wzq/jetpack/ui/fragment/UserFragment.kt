package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.wzq.jetpack.R

class UserFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = requireActivity().intent.getIntExtra("type", 0)
        with(findNavController()) {
            when (type) {
                0 -> {
                    navigate(R.id.user_to_collect)
                }
                1 -> {
                    navigate(R.id.user_to_about)
                }
                2 -> {
                    navigate(R.id.user_to_todo)
                }
            }
        }
    }

}