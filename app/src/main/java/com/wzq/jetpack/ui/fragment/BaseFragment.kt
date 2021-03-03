package com.wzq.jetpack.ui.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by wzq on 2019-07-12
 *
 */
open class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)
}
