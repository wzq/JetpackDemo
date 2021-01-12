package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.NetworkStateListener
import com.wzq.jetpack.databinding.ActivityMainBinding
import com.wzq.jetpack.util.systemBarMode
import com.wzq.jetpack.util.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lookNetwork()
//        val vm = System.getProperty("java.vm.version")
//        Timber.i("Android VM Version is $vm.")

        WindowCompat.setDecorFitsSystemWindows(window, false)
        systemBarMode(false)
        
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav) as? NavHostFragment ?: return
        val nav = hostFragment.navController
        binding.bottomNavigationBar.setupWithNavController(nav)
    }

    private fun lookNetwork() {
        NetworkStateListener().observe(this) {
            if (!it) toast("网路连接失败，请检查网路！", Toast.LENGTH_LONG)
        }
    }
}
