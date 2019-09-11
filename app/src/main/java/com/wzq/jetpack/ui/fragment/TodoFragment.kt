package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentTodoBinding
import com.wzq.jetpack.viewmodel.TodoViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

class TodoFragment: BaseFragment() {

    val viewModel by viewModels<TodoViewModel> { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoBinding.inflate(inflater, container, false)
        viewModel.todoList.observe(this, Observer {
            println(it?.toString())
        })
        binding.todoEdit.setOnClickListener { findNavController().navigate(R.id.todo_to_edit, Bundle()) }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}