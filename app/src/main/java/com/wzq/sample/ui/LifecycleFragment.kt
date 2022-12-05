package com.wzq.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * create by wzq on 2022/9/21
 * if use auto save view state, need add a view id.
 */
abstract class LifecycleFragment : BaseFragment() {
    private var savedView: View? = null

    /**
     * only in view not ready
     */
    abstract fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?

    open fun updateView(currentView: View?): View? {
        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (savedView == null) {
            savedView = createView(inflater, container, savedInstanceState)
        } else {
            updateView(savedView)?.apply {
                savedView = this
            }
        }
        return savedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        onFreedView()
    }

    open fun onFreedView() {
        savedView = null
    }
}