package com.wzq.jetpack.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wzq.jetpack.databinding.FragmentTestShadowBinding

/**
 * create by wzq on 2020/11/23
 *
 */

class ShadowPage : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView")
        return FragmentTestShadowBinding.inflate(inflater, container, false).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        println("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        println("onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate")

        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        println("onStart")

        super.onStart()
    }

    override fun onResume() {
        println("onResume")

        super.onResume()
    }

    override fun onPause() {
        println("onPause")

        super.onPause()
    }

    override fun onStop() {
        println("onStop")

        super.onStop()
    }

    override fun onDestroyView() {
        println("onDestroyView")

        super.onDestroyView()
    }

    override fun onDestroy() {
        println("onDestroy")

        super.onDestroy()
    }

    override fun onDetach() {
        println("onDetach")

        super.onDetach()
    }
}