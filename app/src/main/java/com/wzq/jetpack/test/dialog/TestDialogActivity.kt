package com.wzq.jetpack.test.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wzq.jetpack.R

/**
 * create by wzq on 2021/1/8
 *
 */
class TestDialogActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dialog)
        lifecycleScope.launchWhenStarted {
            DialogA().show(supportFragmentManager, "1")
        }
    }
}