package com.wzq.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.adapter.ArticleAdapter
import com.wzq.jetpack.ui.weiget.SimpleDecoration
import com.wzq.jetpack.viewmodel.CategoryViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory
import timber.log.Timber

class CategoryDetailFragment : BaseFragment(){

    companion object {
        fun instance(id: Int): CategoryDetailFragment {
            val fragment = CategoryDetailFragment()
            fragment.arguments = Bundle().apply {
                putInt("id", id)
            }
            return fragment
        }
    }

    private val viewModel by viewModels<CategoryViewModel> { ViewModelFactory() }

    private val adapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category_detail, container, false)
        val id = arguments?.getInt("id") ?: 0
        Timber.i("category detail id = $id")

        val listView = root.findViewById<RecyclerView>(R.id.category_detail_list)
        listView.addItemDecoration(SimpleDecoration(activity, R.color.line_gray))
        listView.adapter = adapter

        viewModel.getArticleList(id).observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return root
    }
}