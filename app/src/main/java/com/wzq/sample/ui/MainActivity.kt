package com.wzq.sample.ui

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.wzq.sample.R

class MainActivity : BaseActivity() {

//    private lateinit var jankState: JankStats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindNavHost()

//        jankState = JankStats.createAndTrack(
//            window = window, Dispatchers.Default.asExecutor(), jankFramListener
//        )
    }

    override fun onResume() {
        super.onResume()
//        jankState.isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
//        jankState.isTrackingEnabled = false
    }

//    private val jankFramListener = JankStats.OnFrameListener {
//        if (it.isJank) Timber.w("jank state frame data --> $it")
//    }

    private fun bindNavHost() {
        val hostFragment = NavHostFragment.create(R.navigation.nav_main)
        supportFragmentManager.commit {
            replace(android.R.id.content, hostFragment)
            setPrimaryNavigationFragment(hostFragment)
        }
    }
}