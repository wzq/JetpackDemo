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
abstract class LifecycleFragment : BaseFragment() {
    private var savedView: View? = null

    /**
     * only in view not ready
     */
    abstract fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (savedView == null) {
            savedView = initView(inflater, container, savedInstanceState)
        }
        return savedView
    }

    override fun onDestroy() {
        super.onDestroy()
        onReleasedView()
    }

    /**
     * released [savedView] when fragment destroyed
     */
    open fun onReleasedView() {
        savedView = null
    }
}