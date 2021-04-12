package com.wzq.sample.util

import android.view.View
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

/**
 * create by wzq on 2021/4/12
 *
 */
class ImeInsetsCallback(val view: View) :
    WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {

    override fun onPrepare(animation: WindowInsetsAnimationCompat) {
        Timber.d("onPrepare")
        super.onPrepare(animation)
    }

    override fun onStart(
        animation: WindowInsetsAnimationCompat,
        bounds: WindowInsetsAnimationCompat.BoundsCompat
    ): WindowInsetsAnimationCompat.BoundsCompat {
        Timber.d("onStart --- $bounds")
        return super.onStart(animation, bounds)
    }

    override fun onProgress(
        insets: WindowInsetsCompat,
        runningAnimations: MutableList<WindowInsetsAnimationCompat>
    ): WindowInsetsCompat {
        val imeBottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
        val navBottom = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        val offset = imeBottom - navBottom
        if (offset > 0) {
            view.translationY = -offset.toFloat()
        } else {
            view.translationY = 0f
        }
        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimationCompat) {
        Timber.d("onEnd")
        super.onEnd(animation)
    }
}