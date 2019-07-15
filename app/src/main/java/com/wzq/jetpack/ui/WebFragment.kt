package com.wzq.jetpack.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import com.wzq.jetpack.databinding.FragmentWebBinding


/**
 * Created by wzq on 2019-07-15
 *
 */
class WebFragment: BaseFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentWebBinding.inflate(inflater, container, false)
        val url = activity?.intent?.getStringExtra("url") ?: ""

        if (url.isNotEmpty()) {
            config(binding)
            binding.web.loadUrl(url)
        }

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun config(binding: FragmentWebBinding) {
        val webSettings = binding.web.settings
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        //设置编码
        webSettings.defaultTextEncodingName = "utf-8"
        //设置此属性，可任意比例缩放
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        //webView自适应
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL

        //开启Localstorage
        webSettings.domStorageEnabled = true
        val appCachePath = activity?.cacheDir?.absolutePath
        webSettings.setAppCachePath(appCachePath)
        webSettings.allowFileAccess = true
        webSettings.setAppCacheEnabled(true)


        //设置背景颜色 透明
        binding.web.setBackgroundColor(Color.argb(0, 0, 0, 0))
        //设置监听事件
        binding.web.webViewClient = Client()

        binding.web.webChromeClient = ChromeClient(binding.progress)
    }

    class Client : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    inner class ChromeClient(val progress: ProgressBar): WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            activity?.title = title
        }
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            progress.progress = newProgress
            if (newProgress == 100) {
                progress.visibility = View.GONE
            }

            super.onProgressChanged(view, newProgress)
        }
    }
}