package com.wzq.app2.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

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

//        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
//            val ins = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            view.updatePadding(top = ins.top, bottom = ins.bottom)
//            insets
//        }
    }
}