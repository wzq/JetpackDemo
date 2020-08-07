package com.wzq.jetpack.ui.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.weiget.DrawShadow
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DrawShadow.sRoundRectHelper = DrawShadow.RoundRectHelper { canvas, bounds, cornerRadius, paint ->
            canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, paint)
        }

        logo.background = DrawShadow(resources,
            ColorStateList.valueOf(
                   ContextCompat.getColor(requireContext(),R.color.white)
            ), 16f, 8f, 16f)

//
        edit.setOnApplyWindowInsetsListener { v, insets ->
            println(111)
            insets
        }
    }
}