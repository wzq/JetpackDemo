package com.wzq.sample.util

import androidx.navigation.*
import com.wzq.sample.R

/**
 * create by wzq on 2022/11/8
 *
 */

fun NavController.jumpTo(
    directions: NavDirections, block: (NavOptionsBuilder.() -> Unit)? = null
) {
    navigate(directions, navOptions {
        anim {
            enter = R.anim.slide_enter
            exit = R.anim.slide_exit
            popEnter = R.anim.slide_pop_enter
            popExit = R.anim.slide_pop_exit
        }
        block?.invoke(this)
    })
}