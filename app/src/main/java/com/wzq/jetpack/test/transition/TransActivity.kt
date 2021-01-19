package com.wzq.jetpack.test.transition

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.transition.ArcMotion
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityTestAnimTBinding
import com.wzq.jetpack.util.GlideApp
import com.wzq.jetpack.util.ext.dp

/**
 * create by wzq on 2021/1/5
 *
 */
class TransActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestAnimTBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trans = MaterialContainerTransform().apply {
            startView = binding.fabTop
            endView = binding.fabBottom

            addTarget(binding.fabBottom)

            setPathMotion(ArcMotion())
            scrimColor = Color.TRANSPARENT

            duration = 5000
        }

        binding.fabTop.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.root, trans)
            it.isVisible = false
            binding.fabBottom.isVisible = true
        }


        val size = 200.dp.toInt()
        GlideApp.with(this).load(R.drawable.sample)
            .override(size, size).into(binding.img)

    }
}