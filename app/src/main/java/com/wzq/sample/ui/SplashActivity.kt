package com.wzq.sample.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wzq.sample.R
import com.wzq.sample.ui.main.MainActivity

/**
 * create by wzq on 2021/4/6
 *
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<View>(R.id.btn).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}