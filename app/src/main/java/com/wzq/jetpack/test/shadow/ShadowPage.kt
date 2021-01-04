package com.wzq.jetpack.test.shadow

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentTestShadowBinding
import com.wzq.jetpack.util.dp

/**
 * create by wzq on 2020/11/23
 *
 */

class ShadowPage : Fragment(R.layout.fragment_test_shadow) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTestShadowBinding.bind(view)

        val drawable = ShadowDrawable(requireContext())
        drawable.setShadowColor(Color.RED)
        drawable.elevation = 32.dp
        binding.content.background = drawable
    }
}