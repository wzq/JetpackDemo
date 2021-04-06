package com.wzq.app2.ui.main

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.app2.R
import com.wzq.app2.ui.BaseFragment
import com.wzq.app2.ui.main.home.CategoryFragment
import com.wzq.app2.ui.main.home.HomeFragment
import com.wzq.app2.ui.main.home.ProjectFragment

/**
 * create by wzq on 2021/4/6
 *
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val factory: BottomNavFragmentFactory = {
        when (it.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_category -> CategoryFragment()
            R.id.navigation_project -> ProjectFragment()
            else -> throw IllegalArgumentException("can not get fragment ${it.title}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<BottomNavigationView>(R.id.nav_view).setupWithFactory(
            R.id.content,
            fragmentManager = childFragmentManager,
            fragmentFactory = factory
        )
    }
}