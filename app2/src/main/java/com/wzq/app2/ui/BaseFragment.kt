package com.wzq.app2.ui

import androidx.fragment.app.Fragment

/**
 * create by wzq on 2021/4/6
 *
 */
abstract class BaseFragment: Fragment {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)
}