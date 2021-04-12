package com.wzq.sample.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.webkit.WebResourceErrorCompat
import androidx.webkit.WebViewClientCompat
import com.wzq.sample.databinding.FragmentWebBinding
import com.wzq.sample.ui.BaseFragment
import timber.log.Timber

/**
 * Created by wzq on 2019-07-15
 *
 */
class WebFragment : BaseFragment() {

    private lateinit var binding: FragmentWebBinding

    private val webPageState = MutableLiveData<Boolean>()
    
    companion object {
        fun newInstance(url: String): WebFragment {
            val args = Bundle()
            args.putString("url", url)
            val fragment = WebFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWebBinding.inflate(inflater, container, false)

        val url = arguments?.getString("url") ?: ""


        binding.webBack.setOnClickListener { back() }
        binding.webClose.setOnClickListener { findNavController().navigateUp() }

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
            Timber.d("web ${binding.web.url} load $isFinish")
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

        webSettings.javaScriptEnabled = true // 开启JS支持

        webSettings.allowFileAccess = false // 是否允许文件访问

        webSettings.domStorageEnabled = true // 开启Localstorage

        webSettings.cacheMode = WebSettings.LOAD_DEFAULT // 设置缓存模式

        // 设置此属性，可任意比例缩放
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        // webView自适应
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        // 设置背景颜色 透明
        binding.web.setBackgroundColor(Color.TRANSPARENT)

        // 设置缓存地址 设置后webview可以离线运行，建议通过设置cacheMode代替 废弃
//        val appCachePath = activity?.cacheDir?.absolutePath
//        webSettings.setAppCachePath(appCachePath)
//        webSettings.setAppCacheEnabled(true)

//        webSettings.savePassword = false //是否保存密码 废弃

        // 设置编码 默认UTF-8
        //  webSettings.defaultTextEncodingName = "utf-8"
    }

    inner class Client(private val pageState: MutableLiveData<Boolean>) : WebViewClientCompat() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Timber.d("onPageStarted- $url -- ${binding.web.url}")
            pageState.postValue(false)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Timber.d("onPageFinished- $url -- ${view?.progress}")
            pageState.postValue(true)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            Timber.d("shouldOverrideUrlLoading url = ${request.url}")
            return super.shouldOverrideUrlLoading(view, request)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceErrorCompat
        ) {
            Timber.d("onReceivedError url = ${request.url}")
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
        }
    }

    class ChromeClient(private val titleView: TextView) : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            titleView.text = title
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            Timber.d("onProgressChanged- ${view?.url} -- ${newProgress}")
        }
    }
}
