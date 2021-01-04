package com.wzq.jetpack.test.transition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.wzq.jetpack.databinding.ActivityTestAnimVBinding

/**
 * create by wzq on 2020/12/31
 *
 */
class VisibilityActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestAnimVBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img1.setOnClickListener {
        }
    }
}