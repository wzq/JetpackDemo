package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.adapter.ArticleAdapter
import com.wzq.jetpack.viewmodel.SearchViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-07-29
 *
 */
class SearchActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val s = v as? Chip ?: return
        searchView?.setQuery(s.text, true)
    }

    private val viewModel by viewModels<SearchViewModel> { ViewModelFactory() }
    private val adapter = ArticleAdapter()
    lateinit var listView: RecyclerView
    lateinit var historyGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val hotTitle = findViewById<TextView>(R.id.search_hot_title)
        listView = findViewById(R.id.search_result)
        listView.adapter = adapter

        historyGroup = findViewById(R.id.search_history)
        viewModel.history.forEach {
            if (it.isNotEmpty()) hotTitle.visibility = View.VISIBLE
            historyGroup.addView(createChip(it))
        }

        val chipGroup = findViewById<ChipGroup>(R.id.search_hot)
        viewModel.hotWords.observe(this, Observer {
            if (it.isNotEmpty()) hotTitle.visibility = View.VISIBLE
            it.forEach { et ->
                chipGroup.addView(createChip(et.name))
            }
        })

        viewModel.searchResult.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun createChip(it: String): Chip {
        val chip = Chip(this)
        chip.text = it
        chip.setOnClickListener(this)
        return chip
    }


    private var searchView: SearchView? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu?.findItem(R.id.search_icon)?.actionView as? SearchView
        searchView?.queryHint = "搜索"
        searchView?.onActionViewExpanded()
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) return  false
                listView.visibility = View.VISIBLE
                searchFront.visibility = View.GONE
                historyGroup.addView(createChip(query), 0)
                viewModel.searchAny(query)
                return false
            }

            val searchFront = findViewById<View>(R.id.search_front)
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    listView.visibility = View.GONE
                    searchFront.visibility = View.VISIBLE
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

}