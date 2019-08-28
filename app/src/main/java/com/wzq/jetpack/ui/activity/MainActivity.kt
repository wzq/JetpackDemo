package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.NetworkStateListener
import com.wzq.jetpack.ui.fragment.BaseFragment
import com.wzq.jetpack.ui.fragment.CategoryFragment
import com.wzq.jetpack.ui.fragment.HomeFragment
import com.wzq.jetpack.ui.fragment.ProjectFragment
import com.wzq.jetpack.util.Router
import com.wzq.jetpack.util.monitor.LoginMonitor

class MainActivity : BaseActivity() {

    private val drawer by lazy { findViewById<DrawerLayout>(R.id.drawer)!! }

    private val loginMonitor = LoginMonitor()

    private var currentIndex = -1

    private val INDEX_RESTORE = "index_restore"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lookNetwork()
        //transparentStatusBar()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDrawer(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        userArea()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            (supportFragmentManager.findFragmentByTag("main-f$currentIndex") as? BaseFragment)?.back2top()
        }

        val ir = savedInstanceState?.getInt(INDEX_RESTORE, -1)
        if (ir == null || ir < 0) {
            navControl(0)
            title = "Demo"
        } else {
            currentIndex = savedInstanceState.getInt(INDEX_RESTORE)
        }
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
            if (it.itemId == R.id.collect_fragment){
                Router.go2collect(this@MainActivity)
            }
            drawer.closeDrawer(GravityCompat.START)
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


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navControl(0)
                title = "Demo"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                navControl(1)
                title = "项目"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                navControl(2)
                title = "体系"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(INDEX_RESTORE, currentIndex)
    }

    private fun lookNetwork(){
        NetworkStateListener().observe(this, Observer {
            if (!it) {
                Toast.makeText(this@MainActivity, "网路连接失败，请检查网路！", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navControl(index: Int) {
        if (currentIndex != index) {
            val trans = supportFragmentManager.beginTransaction()
            val tag = "main-f$index"
            val target = supportFragmentManager.findFragmentByTag(tag)
                ?: (fragmentFactory(index).also { et -> trans.add(R.id.container, et, tag) })
            trans.show(target)
            val old = supportFragmentManager.findFragmentByTag("main-f$currentIndex")
            if (old != null) {
                trans.hide(old)
            }
            currentIndex = index
            trans.commit()
        }
    }

    private fun fragmentFactory(i: Int): BaseFragment = when(i){
        0 -> HomeFragment()
        1 -> ProjectFragment()
        2 -> CategoryFragment()
        else -> throw IllegalArgumentException("can not get fragment $i")
    }

}
