package com.wzq.jetpack.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.wzq.jetpack.R
import com.wzq.jetpack.util.immersive

/**
 * create by wzq on 2020/11/5
 *
 */
class ImmersiveActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_immersive)
        immersive(true)

        val root = findViewById<View>(R.id.root)

        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val ins =  insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = ins.top, bottom = ins.bottom)
            insets
        }
    }
}