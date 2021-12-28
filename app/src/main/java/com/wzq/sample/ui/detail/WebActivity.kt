package com.wzq.sample.ui.detail

import android.os.Bundle
import androidx.fragment.app.commitNow
import androidx.navigation.navArgs
import com.wzq.sample.R
import com.wzq.sample.ui.BaseActivity
import com.wzq.sample.util.systemBarMode

/**
 * create by wzq on 2021/4/7
 *
 */
class WebActivity : BaseActivity() {

    val args: WebActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        systemBarMode(false)
        setContentView(R.layout.activity_web)

        supportFragmentManager.commitNow {
            replace(R.id.content, WebFragment.newInstance(args.url))
        }
    }
}