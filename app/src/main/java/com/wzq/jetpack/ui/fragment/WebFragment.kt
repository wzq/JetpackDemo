package com.wzq.jetpack.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.wzq.jetpack.databinding.FragmentWebBinding


/**
 * Created by wzq on 2019-07-15
 *
 */
class WebFragment: BaseFragment() {

    private lateinit var binding: FragmentWebBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentWebBinding.inflate(inflater, container, false)
        val url = activity?.intent?.getStringExtra("url") ?: ""

        binding.webBack.setOnClickListener { back() }
        binding.webClose.setOnClickListener { activity?.finish() }

        if (url.isNotEmpty()) {
            config(binding)
            binding.web.loadUrl(url)
            binding.webRefresh.setOnClickListener { binding.web.reload() }
        }

        return binding.root
    }

    fun back(){
        if (binding.web.canGoBack()) {
            binding.web.goBack()
        } else {
            activity?.finish()
        }
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

        binding.web.webChromeClient = ChromeClient()
    }

    inner class Client : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.webRefresh.visibility = View.GONE
            binding.webLoading.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.webRefresh.visibility = View.VISIBLE
            binding.webLoading.visibility = View.GONE
        }
    }

    inner class ChromeClient : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            binding.webTitle.text = title
        }
    }
}