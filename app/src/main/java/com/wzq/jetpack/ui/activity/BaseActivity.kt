package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.wzq.jetpack.R
import com.wzq.jetpack.util.Router

/**
 * Created by wzq on 2019-07-15
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView)?.also {
            it.isAppearanceLightStatusBars = false
            it.isAppearanceLightNavigationBars = true
        }
        supportFragmentManager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        if (item.itemId == R.id.main_search) {
            Router.go2search(this)
        }
        return super.onOptionsItemSelected(item)
    }
}
