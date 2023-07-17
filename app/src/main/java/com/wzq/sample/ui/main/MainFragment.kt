package com.wzq.sample.ui.main

import android.os.Bundle
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.sample.NavMainDirections
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentMainBinding
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.ui.main.category.CategoryFragment
import com.wzq.sample.ui.main.home.HomeFragment
import com.wzq.sample.ui.main.project.ProjectFragment
import com.wzq.sample.util.jumpTo

/**
 * create by wzq on 2021/4/6
 *
 */
class MainFragment : BaseFragment() {


    override fun initView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().jumpTo(NavMainDirections.actionGlobalLoginFragment())
        }
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.main_search) {
                findNavController().jumpTo(MainFragmentDirections.actionMainToSearch())
            }
            false
        }

        val fragments = arrayOf<Fragment>(
            HomeFragment(), ProjectFragment(), CategoryFragment()
        )
        setupBottomNav(
            fragments, binding.navView, R.id.content, fragmentManager = childFragmentManager
        )

        return binding.root
    }

    private fun setupBottomNav(
        fragments: Array<Fragment>,
        navView: BottomNavigationView,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager,
        onItemSelected: ((MenuItem) -> Unit)? = null,
    ) {
        if (fragments.isEmpty()) {
            return
        }

        val source = SparseIntArray()
        var currentItem = navView.selectedItemId

        fragmentManager.commitNow {
            navView.menu.forEachIndexed { index, item ->
                val tag = item.title?.toString() ?: "#$index"
                val fragment = fragments[index]
                if (!fragment.isAdded) {
                    add(containerId, fragment, tag)
                }
                if (item.itemId == currentItem) {
                    show(fragment)
                } else {
                    hide(fragment)
                }
                setReorderingAllowed(true)
                source.append(item.itemId, index)
            }

        }

        navView.setOnItemSelectedListener {
            val index = source[it.itemId]
            val fragment = fragments.getOrNull(index)
            if (fragment!= null) {
                fragmentManager.commit {
                    hide(fragments[source.get(currentItem)])
                    show(fragment)
                    currentItem = it.itemId
                }
            }
            true
        }
        navView.setOnItemReselectedListener {
            onItemSelected?.invoke(it)
            // TODO: 2021/4/6
        }

    }
}