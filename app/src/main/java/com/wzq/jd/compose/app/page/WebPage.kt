package com.wzq.jd.compose.app.page

import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

/**
 * create by wzq on 2023/12/4
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebPage(navController: NavHostController, url: String?) {
    val webTitle = remember { mutableStateOf(url ?: "") }
    val isRefreshing = remember { mutableStateOf(false) }
    val isProgressing = remember { mutableStateOf(true) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = webTitle.value, maxLines = 1)
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Default.ArrowBack, null)

                }
            },
            actions = {
                IconButton(onClick = {
                    isRefreshing.value = !isRefreshing.value
                }) {
                    Icon(Icons.Default.Refresh, null)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            CustomWebView(url, webTitle, isProgressing, isRefreshing)

            if (isProgressing.value) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun CustomWebView(
    url: String?,
    titleStr: MutableState<String>,
    isProgressing: MutableState<Boolean>,
    isRefreshing: MutableState<Boolean>
) {
    if (url.isNullOrEmpty()) {
        // TODO: empty view
        return
    }
    AndroidView(modifier = Modifier
        .fillMaxSize(), factory = {
        WebView(it).apply {
            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    println("redirect --> ${request?.url}")
                    return super.shouldInterceptRequest(view, request)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return true
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    titleStr.value = title ?: ""
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    isProgressing.value = newProgress < 100
                }
            }

            loadUrl(url)
        }
    }) {
        if (isRefreshing.value) {
            it.reload()
        }
    }
}