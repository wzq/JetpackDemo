package com.wzq.jetpack.ui.activity

import android.os.Bundle
import com.wzq.jetpack.R

class UserActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "收藏"
    }
}