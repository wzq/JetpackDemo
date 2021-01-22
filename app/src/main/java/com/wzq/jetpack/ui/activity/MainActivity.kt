package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentManager
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.NetworkStateListener
import com.wzq.jetpack.databinding.ActivityMainBinding
import com.wzq.jetpack.ui.fragment.*
import com.wzq.jetpack.ui.weiget.setupWithFactory
import com.wzq.jetpack.util.ext.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lookNetwork()
//        val vm = System.getProperty("java.vm.version")
//        Timber.i("Android VM Version is $vm.")

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationBar.setupWithFactory(
            R.id.host_nav,
            supportFragmentManager
        ) { menu ->
            when (menu.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_qa -> QuestionFragment()
                R.id.navigation_project -> ProjectFragment()
                R.id.navigation_category -> CategoryFragment()
                R.id.navigation_user -> UserFragment()
                else -> throw IllegalArgumentException("can not get fragment ${menu.title}")
            }
        }
    }

    private fun lookNetwork() {
        NetworkStateListener().observe(this) {
            if (!it) toast("网路连接失败，请检查网路！", Toast.LENGTH_LONG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
