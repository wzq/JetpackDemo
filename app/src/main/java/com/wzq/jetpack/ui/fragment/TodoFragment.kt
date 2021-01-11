package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentTodoBinding
import com.wzq.jetpack.ui.adapter.TodoAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.util.toast
import com.wzq.jetpack.viewmodel.TodoViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

class TodoFragment : BaseFragment() {

    private val viewModel by viewModels<TodoViewModel> { ViewModelFactory() }
    private val adapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoBinding.inflate(inflater, container, false)
            .apply { lifecycleOwner = viewLifecycleOwner }
        binding.todoList.addItemDecoration(SimpleDecoration(activity, R.color.line_gray))
        binding.todoList.adapter = adapter
        binding.todoEdit.setOnClickListener {
            findNavController().navigate(
                R.id.todo_to_edit,
                Bundle()
            )
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.todoList.observe(viewLifecycleOwner) {
            if (it == null){
                requireActivity().toast("请登陆")
            } else adapter.submitList(it.datas)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}