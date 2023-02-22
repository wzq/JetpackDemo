package com.wzq.sample.data.remote

import okhttp3.Call
import okhttp3.EventListener
import okhttp3.Response
import timber.log.Timber

/**
 * create by wzq on 2023/2/20
 *
 */
class HttpEventListener : EventListener() {

    override fun cacheHit(call: Call, response: Response) {
        Timber.d("cacheHit---> ${call.request().url}")
    }

    override fun cacheMiss(call: Call) {
        Timber.d("cacheMiss---> ${call.request().url}")
    }

    override fun cacheConditionalHit(call: Call, cachedResponse: Response) {
        Timber.d("cacheConditionalHit---> ${call.request().url}")
    }
}