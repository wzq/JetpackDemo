package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.data.HomeRepo


/**
 * Created by wzq on 2019-07-12
 *
 */
class ViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val vm =  when(modelClass) {
            HomeViewModel::class.java -> {
                HomeViewModel(HomeRepo())
            }
            else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
        }
        return  vm as T
    }

}