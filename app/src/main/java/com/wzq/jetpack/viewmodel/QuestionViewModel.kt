package com.wzq.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wzq.jetpack.data.remote.Linker

class QuestionViewModel : ViewModel() {



    val questions = liveData {
        val data = Linker.api.getQuestionList(1)
        emit(data.data.datas)
    }
}
