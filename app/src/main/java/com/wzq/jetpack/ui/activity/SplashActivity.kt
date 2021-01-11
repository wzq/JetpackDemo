package com.wzq.jetpack.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.wzq.jetpack.R
import com.wzq.jetpack.util.immersive

/**
 * create by wzq on 2021/1/5
 *
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = FrameLayout(this)
        root.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )

        val img = ImageView(this).also {
            val wrap = FrameLayout.LayoutParams.MATCH_PARENT
            it.layoutParams = FrameLayout.LayoutParams(wrap, wrap)
            it.setImageResource(R.drawable.sample)
        }
        root.addView(img)
        root.setBackgroundColor(Color.WHITE)
        setContentView(root)


        immersive(true)
    }
}