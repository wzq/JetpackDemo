package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.wzq.jetpack.R
import com.wzq.jetpack.viewmodel.SearchViewModel
import com.wzq.jetpack.viewmodel.ViewModelFactory


/**
 * Created by wzq on 2019-07-29
 *
 */
class SearchActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelFactory()).get(SearchViewModel::class.java)
        val chipGroup = findViewById<ChipGroup>(R.id.search_hot)
        val hotTitle = findViewById<TextView>(R.id.search_hot_title)

        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            println(1111)
        }


        viewModel.hotWords.observe(this, Observer {
            if (it.isNotEmpty()) hotTitle.visibility = View.VISIBLE
            it.forEach {
                val chip = Chip(this).apply { text = it.name; tag = it }
                chipGroup.addView(chip)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.search_icon)?.actionView as? SearchView
        searchView?.queryHint = "搜索"
        return super.onCreateOptionsMenu(menu)
    }

}