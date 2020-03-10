package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentQuestionBinding
import com.wzq.jetpack.ui.adapter.QuestionAdapter
import com.wzq.jetpack.viewmodel.QuestionViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    private val viewModel: QuestionViewModel by viewModels()

    private val adapter = QuestionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuestionBinding.inflate(inflater, container, false)
        binding.questionList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.questionList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.questions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


}
