package com.wzq.sample.ui

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.wzq.sample.R
import com.wzq.sample.util.EventBus
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindNavHost()
        mainMessageCollector()
    }

    private fun mainMessageCollector() {
        EventBus.subscribe().onEach {
            Timber.d("message ---> $it")
        }.catch {
            it.printStackTrace()
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }

    private fun bindNavHost() {
        val hostFragment = NavHostFragment.create(R.navigation.nav_main)
        supportFragmentManager.commit {
            replace(android.R.id.content, hostFragment)
            setPrimaryNavigationFragment(hostFragment)
        }

//        hostFragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
//            override fun onCreate(owner: LifecycleOwner) {
//                NavMap.newGraph(hostFragment.navController, NavMap.Dest.main, NavMap.id)
//                hostFragment.lifecycle.removeObserver(this)
//            }
//        })

    }
}