package com.wzq.jetpack.util

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.wzq.jetpack.BuildConfig

/**
 * create by wzq on 2021/1/6
 *
 */
@GlideModule
class MyGlideApp : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
//        if (BuildConfig.DEBUG) {
//            builder.setLogLevel(Log.DEBUG)
//        } else {
//            builder.setLogLevel(Log.ERROR)
//        }
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}