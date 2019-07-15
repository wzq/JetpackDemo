package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.data.HomeRepo


/**
 * Created by wzq on 2019-07-12
 *
 */
class ViewModelFactory(private val repo: BaseRepo): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val vm =  when(repo) {
            is HomeRepo -> {
                HomeViewModel(HomeRepo())
            }
            else -> null
        }
        assert(vm != null)
        return  vm as T
    }

}