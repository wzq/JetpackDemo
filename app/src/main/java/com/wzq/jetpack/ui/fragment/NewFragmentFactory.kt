package com.wzq.jetpack.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class NewFragmentFactory : FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        //todo
        return super.instantiate(classLoader, className)
    }
}