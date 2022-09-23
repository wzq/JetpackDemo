package com.wzq.sample.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

/**
 * create by wzq on 2021/4/6
 *
 */
abstract class BaseFragment: Fragment {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(javaClass.simpleName).i("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.tag(javaClass.simpleName).i("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag(javaClass.simpleName).i("onViewCreated")
    }


//    override fun onStart() {
//        super.onStart()
//        Timber.tag(javaClass.simpleName).i("onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Timber.tag(javaClass.simpleName).i("onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Timber.tag(javaClass.simpleName).i("onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Timber.tag(javaClass.simpleName).i("onStop")
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.tag(javaClass.simpleName).i("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(javaClass.simpleName).i("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.tag(javaClass.simpleName).i("onDetach")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.tag(javaClass.simpleName).i("onAttach")
    }
}