package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wzq.jetpack.data.BaseRepo
import com.wzq.jetpack.data.HomeRepo
import com.wzq.jetpack.data.ProjectRepo
import com.wzq.jetpack.data.UserRepo


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
            LoginViewModel::class.java -> {
                LoginViewModel(UserRepo())
            }
            ProjectViewModel::class.java -> {
                ProjectViewModel(ProjectRepo())
            }
            else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
        }
        return  vm as T
    }

}