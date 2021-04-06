package com.wzq.app2.ui.main

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

typealias BottomNavFragmentFactory = (MenuItem) -> Fragment

fun BottomNavigationView.setupWithFactory(
    @IdRes containerId: Int,
    fragmentManager: FragmentManager,
    fragmentFactory: BottomNavFragmentFactory,
    onItemSelected: ((MenuItem) -> Unit)? = null,
) {
    val first = fragmentFactory(this.menu.findItem(selectedItemId))
    var currentItemTag: String = selectedItemId.toString()
    fragmentManager.commit {
        add(containerId, first, currentItemTag)
    }
    setOnNavigationItemSelectedListener {
        val tag = it.itemId.toString()
        val targetFragment =
            fragmentManager.findFragmentByTag(tag) ?: fragmentFactory(it)
        val currentFragment = fragmentManager.findFragmentByTag(currentItemTag)
        fragmentManager.commit {
            if (!targetFragment.isAdded) add(containerId, targetFragment, tag)
            show(targetFragment)
            currentFragment?.apply { hide(this) }
        }
        currentItemTag = tag
        onItemSelected?.invoke(it)
        true
    }
    setOnNavigationItemReselectedListener {
        // TODO: 2021/4/6
    }
}