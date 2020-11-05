package com.wzq.jetpack.ui.activity

import android.os.Bundle
import com.wzq.jetpack.databinding.ActivityTestBinding

/**
 * create by wzq on 2020/11/4
 *
 */
class TestActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}