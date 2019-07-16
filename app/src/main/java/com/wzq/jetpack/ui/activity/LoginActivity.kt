package com.wzq.jetpack.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityLoginBinding


/**
 * Created by wzq on 2019-07-16
 *
 */
class LoginActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }
}