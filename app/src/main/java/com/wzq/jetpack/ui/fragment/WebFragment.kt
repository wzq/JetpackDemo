package com.wzq.jetpack.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.activity.addCallback
import androidx.lifecycle.MutableLiveData
import com.wzq.jetpack.databinding.FragmentWebBinding
import timber.log.Timber


/**
 * Created by wzq on 2019-07-15
 *
 */
class WebFragment : BaseFragment() {

    private lateinit var binding: FragmentWebBinding

    private val webPageState = MutableLiveData<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isEnabled = false; remove()
            back()
        }
        webPageState.observe(viewLifecycleOwner) { isFinish ->
            if (isFinish) {
                binding.webRefresh.visibility = View.VISIBLE
                binding.webLoading.visibility = View.GONE
            } else {
                binding.webRefresh.visibility = View.GONE
                binding.webLoading.visibility = View.VISIBLE
            }
        }
    }

    private fun back() {
        if (binding.web.canGoBack()) {
            binding.web.goBack()
        } else {
            activity?.finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun config(binding: FragmentWebBinding) {
        val webSettings = binding.web.settings
        binding.web.webViewClient = Client(webPageState)
        binding.web.webChromeClient = ChromeClient(binding.webTitle)

        webSettings.javaScriptEnabled = true //开启JS支持

        webSettings.allowFileAccess = false //是否允许文件访问

        webSettings.domStorageEnabled = true //开启Localstorage

        webSettings.cacheMode = WebSettings.LOAD_DEFAULT //设置缓存模式

        //设置此属性，可任意比例缩放
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        //webView自适应
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        //设置背景颜色 透明
        binding.web.setBackgroundColor(Color.TRANSPARENT)

        //设置缓存地址 设置后webview可以离线运行，建议通过设置cacheMode代替 废弃
//        val appCachePath = activity?.cacheDir?.absolutePath
//        webSettings.setAppCachePath(appCachePath)
//        webSettings.setAppCacheEnabled(true)

//        webSettings.savePassword = false //是否保存密码 废弃

        //设置编码 默认UTF-8
        //  webSettings.defaultTextEncodingName = "utf-8"
    }


    class Client(private val pageState: MutableLiveData<Boolean>) : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            pageState.postValue(false)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            pageState.postValue(true)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            Timber.d("shouldOverrideUrlLoading url = ${request?.url}")
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    class ChromeClient(private val titleView: TextView) : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            titleView.text = title
        }
    }
}