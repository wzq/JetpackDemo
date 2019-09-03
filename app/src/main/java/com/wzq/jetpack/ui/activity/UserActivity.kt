package com.wzq.jetpack.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import com.wzq.jetpack.R

class UserActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val type = intent.getIntExtra("type", 0)

        val controller = findNavController(R.id.nav_user)

        when(type){
            0 -> {
                title = "收藏"
                controller.navigate(R.id.user_collect)
            }
            1 -> {
                controller.navigate(R.id.user_about)
                title = "关于"
            }
        }
    }
}