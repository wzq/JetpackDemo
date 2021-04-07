package com.wzq.app2.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import timber.log.Timber

/**
 * create by wzq on 2021/4/6
 *
 */
abstract class BaseActivity : AppCompatActivity {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        Timber.tag(javaClass.simpleName).i("onCreate")

//        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
//            val ins = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            view.updatePadding(top = ins.top, bottom = ins.bottom)
//            insets
//        }
    }


    override fun onStart() {
        super.onStart()
        Timber.tag(javaClass.simpleName).i("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(javaClass.simpleName).i("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(javaClass.simpleName).i("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(javaClass.simpleName).i("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(javaClass.simpleName).i("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.tag(javaClass.simpleName).i("onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Timber.tag(javaClass.simpleName).i("onNewIntent")
    }
}