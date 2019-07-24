package com.wzq.jetpack.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.wzq.jetpack.R
import com.wzq.jetpack.model.result.LoginResult
import com.wzq.jetpack.ui.fragment.BaseFragment
import com.wzq.jetpack.ui.fragment.CategoryFragment
import com.wzq.jetpack.ui.fragment.HomeFragment
import com.wzq.jetpack.ui.fragment.ProjectFragment
import com.wzq.jetpack.util.Preference
import com.wzq.jetpack.util.Prefs
import com.wzq.jetpack.util.Router
import com.wzq.jetpack.util.transparentStatusBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {
    private val fragments = arrayListOf(
        HomeFragment(),
        ProjectFragment(),
        CategoryFragment()
    )

    private val userName by Preference(Prefs.USER_NAME, "点击登录")

    private var currentPage: Int = -1

    private val drawer by lazy { findViewById<DrawerLayout>(R.id.drawer)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentPage = savedInstanceState?.getInt("index") ?: 0
        EventBus.getDefault().register(this)
        //transparentStatusBar()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDrawer(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        switchPage(currentPage)
        userArea()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            fragments[currentPage].back2top()
        }
    }

    private var headName: TextView? = null
    private var headImg: ImageView? = null

    private fun userArea() {
        val area = findViewById<NavigationView>(R.id.navigation_view)
        val head = area.getHeaderView(0)
        headImg = head.findViewById<ImageView>(R.id.user_head)
        headName = head.findViewById(R.id.user_name)
        headName?.text = userName
        headImg?.setOnClickListener {
            if (Prefs.get(Prefs.USER_ID, 0) == 0)
            Router.go2login(this@MainActivity)
        }
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

    private fun switchPage(index: Int) {
        val page = fragments[index]

        if(page.isAdded && index == currentPage) return
        val trans = supportFragmentManager.beginTransaction()
        if (page.isAdded){
            trans.show(page)
        }else {
            trans.add(R.id.container, page, page.javaClass.name).show(page)
        }
        if (currentPage > -1) {
            val pageNow = fragments[currentPage]
            trans.hide(pageNow)
        }
        trans.commit()
        currentPage = index
    }

//    private fun createPage(tag: String): BaseFragment =
//        when (tag) {
//            fragments[0] -> HomeFragment()
//            fragments[1] -> ProjectFragment()
//            fragments[2] -> CategoryFragment()
//            else -> BaseFragment()
//        }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchPage(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchPage(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchPage(2)
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserLogin(result: LoginResult) {
        headName?.text = result.data.nickname
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("index", currentPage)
    }
}
