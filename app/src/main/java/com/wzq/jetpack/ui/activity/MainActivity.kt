package com.wzq.jetpack.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.jetpack.R
import com.wzq.jetpack.ui.fragment.BaseFragment
import com.wzq.jetpack.ui.fragment.CategoryFragment
import com.wzq.jetpack.ui.fragment.HomeFragment
import com.wzq.jetpack.ui.fragment.ProjectFragment

class MainActivity : BaseActivity() {
    private val fragments = arrayListOf(
        HomeFragment(),
        ProjectFragment(),
        CategoryFragment()
    )

    var currentPage: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        switchPage(fragments[0])
    }

    private fun switchPage(page: BaseFragment) {
        if(page == currentPage) return
        val trans = supportFragmentManager.beginTransaction()
        if (page.isAdded){
            trans.show(page)
        }else {
            trans.add(R.id.container, page).show(page)
        }
        if (currentPage != null) {
            trans.hide(currentPage!!)
        }
        trans.commit()
        currentPage = page
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchPage(fragments[0])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchPage(fragments[1])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchPage(fragments[2])
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
