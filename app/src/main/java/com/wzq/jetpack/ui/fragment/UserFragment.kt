package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.wzq.jetpack.R

class UserFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = requireActivity().intent.getIntExtra("type", 0)
        with(findNavController()) {
            val did = when (type) {
                0 -> {
                    R.id.user_collect
                }
                1 -> {
                    R.id.user_about
                }
                2 -> {
                    R.id.user_todo
                }
                else -> return@with
            }
            graph.startDestination = did //通过设置START ID 使 navigateUp() 能正常推出

            navigate(did, null, navOptions {
                popUpTo(R.id.user) { inclusive = true }  //将占位fragment 弹出栈
            })
        }
    }

}