package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.NetworkStateListener
import com.wzq.jetpack.databinding.ActivityMainBinding
import com.wzq.jetpack.ui.fragment.*
import com.wzq.jetpack.util.Router
import com.wzq.jetpack.util.monitor.LoginMonitor
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val drawer by lazy { findViewById<DrawerLayout>(R.id.drawer)!! }

    private val loginMonitor = LoginMonitor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lookNetwork()
        val vm = System.getProperty("java.vm.version")
        Timber.i("Android VM Version is $vm.")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initDrawer(binding.toolbar)
        userArea()
        initBottomView(binding.navView)

//        binding.fab.setOnClickListener{
//            (supportFragmentManager.findFragmentByTag("main-f$currentIndex") as? BaseFragment)?.back2top()
//        }

        // TODO: 2020/11/5 open test page
        binding.fab.setOnClickListener {
            Router.go2test(it.context)
        }

    }

    private fun initBottomView(bottomNavigationView: BottomNavigationView) {
        val nav = findNavController(R.id.nav_main)
        bottomNavigationView.setupWithNavController(nav)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        supportActionBar?.title = title
    }

    private fun userArea() {
        val nav = findViewById<NavigationView>(R.id.navigation_view)
        nav.setNavigationItemSelectedListener {
            if (loginMonitor.isLogin()) {
                when (it.itemId) {
                    R.id.question_fragment -> Router.go2question(this@MainActivity)
                    R.id.collect_fragment -> Router.go2collect(this@MainActivity)
                    R.id.about_fragment -> Router.go2about(this@MainActivity)
                    R.id.todo_fragment -> Router.go2todo(this@MainActivity)
                }
//                drawer.closeDrawer(GravityCompat.START)
            } else {
                Snackbar.make(window.decorView.findViewById<View>(android.R.id.content),
                    "请登录", Snackbar.LENGTH_LONG)
                    .setAction("去登录") {
                        Router.go2login(this@MainActivity)
                    }.show()
            }
            false
        }

        val head = nav.getHeaderView(0)
        val headName = head.findViewById<TextView>(R.id.user_name)
        head.findViewById<ImageView>(R.id.user_head)?.setOnClickListener {
            //if (Prefs.get(Prefs.USER_ID, 0) == 0)
            Router.go2login(this@MainActivity)
            drawer.closeDrawer(GravityCompat.START)
        }

        loginMonitor.observe(this, Observer {
            headName.text = it
        })
    }

    private fun initDrawer(toolbar: Toolbar?) {
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun lookNetwork(){
        NetworkStateListener().observe(this, Observer {
            if (!it) {
                Toast.makeText(this@MainActivity, "网路连接失败，请检查网路！", Toast.LENGTH_LONG).show()
            }
        })
    }



}
