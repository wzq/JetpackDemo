package com.wzq.jetpack.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.viewmodel.ViewModelFactory
import kotlin.reflect.KClass


/**
 * Created by wzq on 2019-07-12
 *
 */
open class BaseFragment: Fragment() {

    fun <T: ViewModel> createViewModel(repo: BaseRepo, clazz: Class<T>) = ViewModelProviders.of(this, ViewModelFactory(repo)).get(clazz)

}