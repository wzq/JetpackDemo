package com.wzq.jetpack.ui.weiget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import timber.log.Timber

/**
 * create by wzq on 2020/12/29
 *
 * clipChild = false
 */
class ShadowDrawable private constructor(shapeAppearanceModel: ShapeAppearanceModel) :
    MaterialShapeDrawable(shapeAppearanceModel) {

    constructor(context: Context, corner: Float = 0f) : this(
        ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, corner).build()
    ) {
        shadowCompatibilityMode = SHADOW_COMPAT_MODE_ALWAYS
        initializeElevationOverlay(context)
        setTint(Color.TRANSPARENT)
        paintStyle = Paint.Style.FILL
    }

    override fun getShadowOffsetX(): Int {
        val x = super.getShadowOffsetX()
        Timber.i("shadow offset x = $x")
        return x
    }

    override fun getShadowOffsetY(): Int {
        val y = super.getShadowOffsetY()
        Timber.i("shadow offset y = $y")
        return 0
    }

    override fun getShadowRadius(): Int {
        val r = super.getShadowRadius()
        Timber.i("shadow corner r = $r")
        return r
    }
}