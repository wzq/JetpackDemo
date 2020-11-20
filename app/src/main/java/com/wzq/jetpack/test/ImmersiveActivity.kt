package com.wzq.jetpack.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wzq.jetpack.R
import com.wzq.jetpack.util.transparentStatusBar

/**
 * create by wzq on 2020/11/5
 *
 */
class ImmersiveActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_immersive)
        transparentStatusBar()
    }
}