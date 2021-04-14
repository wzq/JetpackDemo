package com.wzq.sample.weidget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.wzq.sample.util.getActionBarHeight
import com.wzq.sample.util.getStatusBarHeight

/**
 * create by wzq on 2021/4/13
 *
 */
class TitleBar : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        val ah = context.getActionBarHeight()
        doOnPreDraw {
            val sh = it.getStatusBarHeight()
            it.updatePadding(
                top = sh
            )
            it.updateLayoutParams {
                height = ah + sh
            }
        }
        initDefaultView()
    }

    private fun initDefaultView(){
        // TODO: 2021/4/13
    }
}