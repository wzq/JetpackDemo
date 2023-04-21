package com.wzq.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * create by wzq on 2022/9/21
 * In navigation.
 * If use auto save view state, need add a view id.
 */
abstract class BaseFragment : LogFragment() {

    private var cacheView: View? = null

    open fun isUseViewCache() = true

    /**
     * create new root view
     */
    abstract fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return if (isUseViewCache()) {
            cacheView ?: initView(inflater, container, savedInstanceState).also {
                cacheView = it
            }
        } else {
            initView(inflater, container, savedInstanceState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onReleasedView()
    }

    /**
     * released [cacheView] when fragment destroyed
     */
    open fun onReleasedView() {
        cacheView = null
    }
}