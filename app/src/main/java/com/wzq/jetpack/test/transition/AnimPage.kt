package com.wzq.jetpack.test.transition

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.wzq.jetpack.databinding.FragmentTestAnimBinding
import com.wzq.jetpack.util.dp
import com.wzq.jetpack.util.dp2px

/**
 * create by wzq on 2020/11/23
 *
 */
class AnimPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTestAnimBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTestAnimBinding.bind(view)

        val model = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, 0.dp).build()


        val drawable = Test(model)
        drawable.shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
        drawable.initializeElevationOverlay(requireContext())
        drawable.setTint(Color.TRANSPARENT)
        drawable.paintStyle = Paint.Style.FILL
        drawable.setShadowColor(Color.BLUE)
        drawable.elevation = 32.dp
        binding.img.background = (drawable)
    }

    class Test(shapeAppearanceModel: ShapeAppearanceModel) :
        MaterialShapeDrawable(shapeAppearanceModel) {

        override fun getShadowOffsetX(): Int {
            val x = super.getShadowOffsetX()
            println("x = $x")
            return x
        }

        override fun getShadowOffsetY(): Int {
            val y = super.getShadowOffsetY()
            println("y = $y")
            return 0
        }

        override fun getShadowRadius(): Int {
            val r = super.getShadowRadius()
            println("r = $r")
            return r
        }
    }

}