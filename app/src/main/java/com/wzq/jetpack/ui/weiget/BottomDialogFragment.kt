package com.wzq.jetpack.ui.weiget

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.wzq.jetpack.R

/**
 * create by wzq on 2020/11/30
 *
 */
abstract class BottomDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object :
            Dialog(requireContext(), R.style.Theme_MaterialComponents_BottomSheetDialog) {
            override fun onCreate(savedInstanceState: Bundle?) {
                window?.setGravity(Gravity.BOTTOM)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }
}