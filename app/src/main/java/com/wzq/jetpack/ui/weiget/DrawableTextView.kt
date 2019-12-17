package com.wzq.jetpack.ui.weiget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by wzq on 2017/7/27.
 */
class DrawableTextView : AppCompatTextView {
    constructor(
        context: Context?, attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    constructor(context: Context?) : super(context)

    override fun onDraw(canvas: Canvas) {
        val drawables = compoundDrawables
        val drawableLeft = drawables[0]
        val pd = paddingStart + paddingEnd
        val textWidth = paint.measureText(text.toString())
        val offset = if (drawableLeft != null) {
            val drawablePadding = compoundDrawablePadding
            val drawableWidth = drawableLeft.intrinsicWidth
            val bodyWidth = textWidth + drawableWidth + drawablePadding
            bodyWidth
        } else {
            textWidth
        }
        canvas.translate((width - offset - pd) / 2, 0f)
        super.onDraw(canvas)
    }
}