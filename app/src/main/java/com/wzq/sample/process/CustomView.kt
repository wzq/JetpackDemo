package com.wzq.sample.process

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.wzq.sample.util.dp

/**
 * create by wzq on 2023/5/22
 *
 */
class CustomViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEach {
            it.layout(0, 0, 200.dp.toInt(), 200.dp.toInt())
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("dispatchTouchEvent --> ${MotionEvent.actionToString(ev?.action!!)}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("onInterceptTouchEvent --> ${MotionEvent.actionToString(ev?.action!!)}")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("parent --> ${MotionEvent.actionToString(event?.action!!)}")
        return true
    }
}

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println(MotionEvent.actionToString(event?.action!!))
        parent.requestDisallowInterceptTouchEvent(event.action == MotionEvent.ACTION_DOWN)
        return true
    }
}
