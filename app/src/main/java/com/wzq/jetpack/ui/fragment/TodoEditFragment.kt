package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wzq.jetpack.databinding.FragmentTodoEditBinding
import com.wzq.jetpack.util.timeFormat
import com.wzq.jetpack.viewmodel.TodoViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

class TodoEditFragment: BaseFragment() {
    private val viewModel by viewModels<TodoViewModel> { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTodoEditBinding.inflate(inflater, container, false)

        binding.todoSubmit.setOnClickListener {

            val params = mutableMapOf<String, String>()
            params["title"] = binding.todoEditTitle.text.toString()
            params["content"] = binding.todoEditContent.text.toString()
            params["date"] = timeFormat(System.currentTimeMillis())
            viewModel.submit(params)
        }
        return binding.root
    }
}