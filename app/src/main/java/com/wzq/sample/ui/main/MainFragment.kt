package com.wzq.sample.ui.main

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.sample.NavMainDirections
import com.wzq.sample.R
import com.wzq.sample.databinding.FragmentMainBinding
import com.wzq.sample.ui.LifecycleFragment
import com.wzq.sample.ui.main.category.CategoryFragment
import com.wzq.sample.ui.main.home.HomeFragment
import com.wzq.sample.ui.main.project.ProjectFragment
import com.wzq.sample.util.jumpTo

/**
 * create by wzq on 2021/4/6
 *
 */
class MainFragment : LifecycleFragment() {

    private val fragments = listOf(
        HomeFragment(), ProjectFragment(), CategoryFragment()
    )

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        setupBottomNav(
            binding.navView, R.id.content, fragmentManager = childFragmentManager
        )
        return binding.root
    }

    private fun setupBottomNav(
        navView: BottomNavigationView,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager,
        onItemSelected: ((MenuItem) -> Unit)? = null,
    ) {
        val source = SparseArray<Fragment>()
        var currentItem = navView.selectedItemId
        navView.menu.forEachIndexed { index, item ->
            val tag = "navFragment#$index"
            val fragment = fragments[index]
            fragmentManager.commitNow {
                if (!fragment.isAdded) {
                    add(containerId, fragment, tag)
                }
                if (item.itemId == currentItem) {
                    show(fragment)
                } else {
                    hide(fragment)
                }
                setReorderingAllowed(true)
            }
            source.append(item.itemId, fragment)
        }


        navView.setOnItemSelectedListener {
            fragmentManager.commitNow {
                hide(source[currentItem])
                show(source[it.itemId])
                currentItem = it.itemId
            }
            true
        }
        navView.setOnItemReselectedListener {
            // TODO: 2021/4/6
        }

    }
}