package com.wzq.sample.ui

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.wzq.sample.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindNavHost()
    }

    private fun bindNavHost() {
        val hostFragment = NavHostFragment.create(R.navigation.nav_main)
        supportFragmentManager.commit {
            replace(android.R.id.content, hostFragment)
            setPrimaryNavigationFragment(hostFragment)
        }
    }
}