package com.wzq.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * create by wzq on 2022/9/21
 *
 */
abstract class LifecycleFragment : BaseFragment() {
    private var savedView: View? = null

    abstract fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?

    open fun onFreedView() {
        savedView = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (savedView == null) {
            savedView = createView(inflater, container, savedInstanceState)
        }
        return savedView
    }

    override fun onDestroy() {
        super.onDestroy()
        onFreedView()
    }
}