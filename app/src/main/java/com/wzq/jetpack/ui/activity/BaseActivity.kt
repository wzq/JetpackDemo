package com.wzq.jetpack.ui.activity

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


/**
 * Created by wzq on 2019-07-15
 *
 */
abstract class BaseActivity: AppCompatActivity(), CoroutineScope by MainScope(){

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}