package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wzq.jetpack.databinding.FragmentTodoEditBinding
import com.wzq.jetpack.util.ext.timeFormat
import com.wzq.jetpack.viewmodel.TodoViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

class TodoEditFragment : BaseFragment() {
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
            viewModel.submit(params).observe(
                viewLifecycleOwner,
                Observer {
                    // todo
                    if (it.errorCode == 0) {
                        Toast.makeText(this.context, "添加成功", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                }
            )
        }
        return binding.root
    }
}
