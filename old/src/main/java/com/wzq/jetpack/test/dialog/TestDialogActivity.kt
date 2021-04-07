package com.wzq.jetpack.test.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.wzq.jetpack.R

/**
 * create by wzq on 2021/1/8
 *
 */
class TestDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dialog)
        lifecycleScope.launchWhenStarted {
            DialogA().show(supportFragmentManager, "1")
        }

        val t1 = findViewById<TextView>(R.id.t1)
        t1.setOnClickListener {
            val clipboardManager =
                ContextCompat.getSystemService(this, ClipboardManager::class.java)
            val data = ClipData.newPlainText("ss_key", t1.text)
            clipboardManager?.setPrimaryClip(data)
        }
    }
}
