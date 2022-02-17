package com.wzq.sample.ui.main

import android.os.Bundle
import com.wzq.sample.R
import com.wzq.sample.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clearFragmentManager()
        setContentView(R.layout.activity_main)
   }

    private fun clearFragmentManager() {
        supportFragmentManager.beginTransaction().also { transition ->
            supportFragmentManager.fragments.forEach {
                transition.remove(it)
            }
        }.commitNow()
    }
}