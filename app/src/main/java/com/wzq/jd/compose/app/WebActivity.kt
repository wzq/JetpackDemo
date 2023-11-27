package com.wzq.jd.compose.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity

/**
 * create by wzq on 2023/11/27
 *
 */
class WebActivity : ComponentActivity() {
    companion object {
        fun open(context: Context, url: String) {
            context.startActivity(Intent(context, WebActivity::class.java).also {
                it.putExtra("url", url)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        setContentView(
            webView, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val uri = intent.getStringExtra("url")
        if (uri.isNullOrEmpty()) {
            finish()
            return
        }
        webView.loadUrl(uri)
    }
}