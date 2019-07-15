package com.wzq.jetpack.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.Linker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    val fragments = arrayListOf(HomeFragment(), BaseFragment(), BaseFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        switchPage(fragments[0])
    }

    private fun switchPage(page: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, page).commit()
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
