package com.wzq.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * create by wzq on 2023/11/14
 *
 */
interface PageLifecycle {

    fun onCreatedPage(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?

    fun onShowed(root: View)

    fun onHidden()

    fun onReleasedPage()
}

abstract class BasePage : Fragment(), PageLifecycle {

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = onCreatedPage(inflater, container, savedInstanceState)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onShowed(view)
    }

    override fun onShowed(root: View) {
    }

    override fun onHidden() {
    }

    override fun onDestroyView() {
        onHidden()
        super.onDestroyView()
    }

    override fun onReleasedPage() {
        rootView = null
    }

}