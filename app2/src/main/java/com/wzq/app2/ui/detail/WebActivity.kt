package com.wzq.app2.ui.detail

import android.os.Bundle
import androidx.fragment.app.commitNow
import androidx.navigation.navArgs
import com.wzq.app2.R
import com.wzq.app2.ui.BaseActivity

/**
 * create by wzq on 2021/4/7
 *
 */
class WebActivity : BaseActivity() {

    val args: WebActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        supportFragmentManager.commitNow {
            replace(R.id.content, WebFragment.newInstance(args.url))
        }
    }
}