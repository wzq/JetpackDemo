package com.wzq.jetpack.test.shadow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.wzq.jetpack.R
import com.wzq.jetpack.util.dp2px

/**
 * create by wzq on 2020/11/23
 *
 */

class ShadowPage : Fragment(R.layout.fragment_test_shadow)

class ShadowView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    /** Gradient start color of 68 which evaluates to approximately 26% opacity.  */
    private val COLOR_ALPHA_START = 0x44

    /** Gradient start color of 20 which evaluates to approximately 8% opacity.  */
    private val COLOR_ALPHA_MIDDLE = 0x14

    private val COLOR_ALPHA_END = 0
    private val edgePositions = floatArrayOf(0f, .5f, 1f)

    val mPaint = Paint(Paint.DITHER_FLAG)

    fun initPaint(canvas: Canvas, bounds: RectF, @ColorInt color: Int = Color.BLACK) {
        mPaint.style = Paint.Style.FILL

        val edgeColors = intArrayOf(
            ColorUtils.setAlphaComponent(color, COLOR_ALPHA_START),
            ColorUtils.setAlphaComponent(color, COLOR_ALPHA_MIDDLE),
            ColorUtils.setAlphaComponent(color, COLOR_ALPHA_END),
        )
        LinearGradient(
            bounds.left,
            bounds.top,
            bounds.left,
            bounds.bottom,
            edgeColors,
            edgePositions,
            Shader.TileMode.CLAMP
        ).also {
            mPaint.shader = it
        }

        canvas.save()
        canvas.drawRect(bounds, mPaint)
        canvas.restore()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val p60 = dp2px(60).toFloat()
        val p120 = dp2px(120).toFloat()
        val rect = RectF(p60, p60, p120, dp2px(66).toFloat())
        initPaint(canvas, rect)
    }
}