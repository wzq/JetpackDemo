package com.wzq.sample.weidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.wzq.sample.App
import com.wzq.sample.R
import com.wzq.sample.util.dp

class SimpleDecoration(
    context: Context? = App.instance,
    private val resId: Int = R.color.line_gray
) : RecyclerView.ItemDecoration() {

    val offset: Int = (1.dp / 2).toInt()

    val paint = Paint().apply {
        if (context != null) {
            color = ContextCompat.getColor(context, resId)
        }
        style = Paint.Style.FILL
        strokeWidth = 0f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        // last position
        if (isLastPosition(view, parent)) {
            return
        }

        outRect.bottom = offset
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + parent.marginStart
        val right = parent.width - left

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            // last position
            if (isLastPosition(child, parent)) {
                return
            }

            val top = child.bottom + params.bottomMargin
            val bottom = top + offset
            val r = Rect(left, top, right, bottom)
            c.drawRect(r, paint)
        }
    }

    private fun isFirstPosition(view: View, parent: RecyclerView): Boolean {
        return parent.getChildAdapterPosition(view) == 0
    }

    private fun isLastPosition(view: View, parent: RecyclerView): Boolean {
        return parent.getChildAdapterPosition(view) == parent.adapter?.itemCount!! - 1
    }
}
