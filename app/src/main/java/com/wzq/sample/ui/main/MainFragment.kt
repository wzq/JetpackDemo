package com.wzq.sample.ui.main

import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.sample.R
import com.wzq.sample.ui.BaseFragment
import com.wzq.sample.ui.main.category.CategoryFragment
import com.wzq.sample.ui.main.home.HomeFragment
import com.wzq.sample.ui.main.project.ProjectFragment

/**
 * create by wzq on 2021/4/6
 *
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView = view.findViewById<BottomNavigationView>(R.id.nav_view)

        setupBottomNav(
            navView,
            R.id.content,
            fragmentManager = childFragmentManager
        )
    }

    private val fragments = listOf(
        HomeFragment(),
        ProjectFragment(),
        CategoryFragment()
    )

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


        navView.setOnNavigationItemSelectedListener {
            fragmentManager.commitNow {
                hide(source[currentItem])
                show(source[it.itemId])
                currentItem = it.itemId
            }
            true
        }
        navView.setOnNavigationItemReselectedListener {
            // TODO: 2021/4/6
        }
    }
}