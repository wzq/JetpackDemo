package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentUserMenuBinding
import com.wzq.jetpack.test.TestActivity
import com.wzq.jetpack.ui.transcation.LARGE_EXPAND_DURATION
import com.wzq.jetpack.util.ext.openPage
import java.util.concurrent.TimeUnit

class UserMenuFragment : BaseFragment(R.layout.fragment_user_menu) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = LARGE_EXPAND_DURATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        exitTransition = MaterialElevationScale(false).apply {
            duration = LARGE_EXPAND_DURATION
        }

        reenterTransition = MaterialElevationScale(true).apply {
            duration = LARGE_EXPAND_DURATION
        }


        val binding = FragmentUserMenuBinding.bind(view)
        binding.b1.setOnClickListener {
            val directions = UserMenuFragmentDirections.userToCollect()
            val extraArgs = FragmentNavigatorExtras(it to getString(R.string.trans_collect))
            findNavController().navigate(directions, extraArgs)
        }

        binding.b2.setOnClickListener {
            val transName = getString(R.string.trans_todo)
            val extraArgs = FragmentNavigatorExtras(it to transName)
            val directions = UserMenuFragmentDirections.userToTodo()
            findNavController().navigate(directions, extraArgs)
        }

        binding.b3.setOnClickListener {
            requireActivity().openPage(TestActivity::class)
        }

        binding.b4.setOnClickListener {
            val transName = getString(R.string.trans_about)
            val extraArgs = FragmentNavigatorExtras(it to transName)
            val directions = UserMenuFragmentDirections.userToAbout()
            findNavController().navigate(directions, extraArgs)
        }
    }

}