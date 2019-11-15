package com.wzq.jetpack.ui.activity

import android.app.SharedElementCallback
import android.content.Context
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
import androidx.lifecycle.liveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.wzq.jetpack.R
import com.wzq.jetpack.data.remote.NetworkStateListener
import com.wzq.jetpack.ui.fragment.*
import com.wzq.jetpack.util.AnimUtils
import com.wzq.jetpack.util.Prefs
import com.wzq.jetpack.util.Router
import com.wzq.jetpack.util.monitor.LoginMonitor
import com.wzq.jetpack.util.threadLog
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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

        animateToolbar(toolbar)
        setExitSharedElementCallback(createSharedElementReenterCallback(this))
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
                when {
                    it.itemId == R.id.collect_fragment -> Router.go2collect(this@MainActivity)
                    it.itemId == R.id.about_fragment -> Router.go2about(this@MainActivity)
                    it.itemId == R.id.todo_fragment -> Router.go2todo(this@MainActivity)
                }
                drawer.closeDrawer(GravityCompat.START)
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
            R.id.navigation_gank -> {
                navControl(3)
                title = "Gank"
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
        3 -> GankFragment()
        else -> throw IllegalArgumentException("can not get fragment $i")
    }

    private fun animateToolbar(toolbar: Toolbar) {
        // this is gross but toolbar doesn't expose it's children to animate them :(
        val t = toolbar.getChildAt(0)
        if (t is TextView) {
            t.apply {
                alpha = 0.4f
                scaleX = 0.8f

                animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .setStartDelay(300)
                    .setDuration(900).interpolator =
                    AnimUtils.getFastOutSlowInInterpolator(this@MainActivity)
            }
        }

    }

    fun createSharedElementReenterCallback(
        context: Context
    ): SharedElementCallback {
        val shotTransitionName = "image"
        val shotBackgroundTransitionName ="name"
        return object : SharedElementCallback() {

            /**
             * We're performing a slightly unusual shared element transition i.e. from one view
             * (image in the grid) to two views (the image & also the background of the details
             * view, to produce the expand effect). After changing orientation, the transition
             * system seems unable to map both shared elements (only seems to map the shot, not
             * the background) so in this situation we manually map the background to the
             * same view.
             */
            override fun onMapSharedElements(
                names: List<String>,
                sharedElements: MutableMap<String, View>
            ) {
                if (sharedElements.size != names.size) {
                    // couldn't map all shared elements
                    sharedElements[shotTransitionName]?.let {
                        // has shot so add shot background, mapped to same view
                        sharedElements[shotBackgroundTransitionName] = it
                    }
                }
            }
        }
    }

}
