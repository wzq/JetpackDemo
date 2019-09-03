package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wzq.jetpack.R

class AboutFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val progress = inflater.inflate(R.layout.test_p, null)
        val root = inflater.inflate(R.layout.fragment_base, container, false)
        container?.addView(progress)
        return root
    }
}