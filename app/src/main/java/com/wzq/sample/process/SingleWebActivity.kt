package com.wzq.sample.process

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

/**
 * create by wzq on 2023/4/6
 * :web_process
 */
class SingleWebActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url")
        if (url.isNullOrEmpty()) {
            finish()
            return
        }

        webView = WebView(this)
        config(webView)

        setContentView(webView)

        webView.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun config(webView: WebView) {
        val webSettings = webView.settings

        webSettings.javaScriptEnabled = true // 开启JS支持

        webSettings.allowFileAccess = false // 是否允许文件访问

        webSettings.domStorageEnabled = true // 开启Localstorage
        webSettings.databaseEnabled = true

        webSettings.cacheMode = WebSettings.LOAD_DEFAULT // 设置缓存模式, 默认是LOAD_DEFAULT

        // 设置此属性，可任意比例缩放
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        // webView自适应
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL

        // 设置编码 默认UTF-8
//        webSettings.defaultTextEncodingName = "utf-8"

        //是否主动加载图片，默认是true
//        webSettings.loadsImagesAutomatically = false

    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }

}