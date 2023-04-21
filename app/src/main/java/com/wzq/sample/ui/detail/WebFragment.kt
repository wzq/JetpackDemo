package com.wzq.sample.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.webkit.WebResourceErrorCompat
import androidx.webkit.WebViewClientCompat
import com.wzq.sample.databinding.FragmentWebBinding
import com.wzq.sample.databinding.ViewWebTitleBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.util.ImeInsetsCallback
import timber.log.Timber

/**
 * Created by wzq on 2019-07-15
 *
 */
class WebFragment : BaseFragment() {

    private lateinit var binding: FragmentWebBinding
    private lateinit var titleBar: ViewWebTitleBinding

    private val webPageState = MutableLiveData<Boolean>()

    private val args: WebFragmentArgs by navArgs()

    override fun isUseViewCache() = false

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.root.updatePadding(bottom = systemInsets.bottom)
            insets
        }
        ViewCompat.setWindowInsetsAnimationCallback(binding.root, ImeInsetsCallback(binding.web))


        titleBar = binding.webTitleBar

        titleBar.webBack.setOnClickListener { back() }
        titleBar.webClose.setOnClickListener { back(true) }

        val url = args.url ?: ""
        if (url.isNotEmpty()) {
            config(binding)
            binding.web.loadUrl(url)
            titleBar.webRefresh.setOnClickListener { binding.web.reload() }
        }

        // 设置背景颜色 透明
        binding.web.setBackgroundColor(Color.TRANSPARENT)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        webPageState.observe(viewLifecycleOwner) { isFinish ->
            Timber.d("web ${binding.web.url} load $isFinish")
            if (isFinish) {
                titleBar.webRefresh.visibility = View.VISIBLE
                titleBar.webLoading.visibility = View.GONE
            } else {
                titleBar.webRefresh.visibility = View.GONE
                titleBar.webLoading.visibility = View.VISIBLE
            }
        }

    }

    private fun back(finish: Boolean = false) {
        if (!finish && binding.web.canGoBack()) {
            binding.web.goBack()
        } else {
            findNavController().navigateUp()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun config(binding: FragmentWebBinding) {
        val webSettings = binding.web.settings
        binding.web.webViewClient = Client(webPageState)
        binding.web.webChromeClient = ChromeClient(titleBar.webTitle)

        webSettings.javaScriptEnabled = true // 开启JS支持

        webSettings.allowFileAccess = false // 是否允许文件访问

        webSettings.domStorageEnabled = true // 开启Localstorage

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

    class Client(private val pageState: MutableLiveData<Boolean>) : WebViewClientCompat() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Timber.d("onPageStarted- ${url == view?.url}")
            pageState.postValue(false)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Timber.d("onPageFinished- $url -- ${view?.progress}")
            pageState.postValue(true)
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            Timber.d("shouldOverrideUrlLoading url = ${request.url}")
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceErrorCompat
        ) {
            Timber.d("onReceivedError url = ${request.url}")
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpError(
            view: WebView,
            request: WebResourceRequest,
            errorResponse: WebResourceResponse
        ) {
            Timber.d("onReceivedHttpError url = ${request.url}")
            super.onReceivedHttpError(view, request, errorResponse)
        }

    }

    class ChromeClient(private val titleView: TextView) : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            titleView.text = title
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            Timber.d("onProgressChanged- ${view?.url} -- $newProgress")
        }
    }
}
