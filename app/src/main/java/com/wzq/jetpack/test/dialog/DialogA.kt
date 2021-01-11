package com.wzq.jetpack.test.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wzq.jetpack.databinding.DialogABinding
import com.wzq.jetpack.ui.weiget.BottomDialogFragment

/**
 * create by wzq on 2021/1/8
 *
 */
class DialogA: BottomDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogABinding.inflate(inflater, container, false).root
    }
}