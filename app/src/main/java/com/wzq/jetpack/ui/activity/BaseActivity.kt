package com.wzq.jetpack.ui.activity

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.wzq.jetpack.R
import com.wzq.jetpack.util.Router
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        if (item?.itemId == R.id.main_search){
            Router.go2search(this)
        }
        return super.onOptionsItemSelected(item)
    }
}