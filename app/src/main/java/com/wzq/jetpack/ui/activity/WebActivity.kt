package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.MenuItem
import com.wzq.jetpack.R


/**
 * Created by wzq on 2019-07-15
 *
 */
class WebActivity: BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

//        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}