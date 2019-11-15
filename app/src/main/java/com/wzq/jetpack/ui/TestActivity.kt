package com.wzq.jetpack.ui

import android.os.Bundle
import androidx.core.view.ViewCompat
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        postponeEnterTransition()
        ViewCompat.setTransitionName(image, "image")
        ViewCompat.setTransitionName(name, "name")
        startPostponedEnterTransition()
    }
}