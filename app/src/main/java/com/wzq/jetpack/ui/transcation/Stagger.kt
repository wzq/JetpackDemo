package com.wzq.jetpack.ui.transcation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.SidePropagation
import androidx.transition.TransitionValues

/**
 * create by wzq on 2020/12/30
 *
 */
class Stagger : Fade(IN) {
    init {
        // This duration is for a single item. See the comment below about propagation.
        duration = LARGE_EXPAND_DURATION / 2
        interpolator = LINEAR_OUT_SLOW_IN
        propagation = SidePropagation().apply {
            setSide(Gravity.BOTTOM)
            // We want the stagger effect to take as long as the duration of a single item.
            // In other words, the last item starts to fade in around the time when the first item
            // finishes animating. The overall animation will take about twice the duration of one
            // item fading in.
            setPropagationSpeed(1f)
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        val view = startValues?.view ?: endValues?.view ?: return null
        // The parent can create an Animator for the fade-in.
        val fadeAnimator = super.createAnimator(sceneRoot, startValues, endValues) ?: return null
        return AnimatorSet().apply {
            playTogether(
                fadeAnimator,
                // We make the view to slide up a little as it fades in.
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, view.height * 0.5f, 0f)
            )
        }
    }
}