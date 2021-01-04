package com.wzq.jetpack.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityTestBinding
import com.wzq.jetpack.test.shadow.ShadowPage
import com.wzq.jetpack.test.transition.AnimListActivity
import com.wzq.jetpack.test.transition.AnimPage
import com.wzq.jetpack.test.video.VideoPage
import com.wzq.jetpack.test.video.VideoWithViewPager2Page
import com.wzq.jetpack.util.openPage

/**
 * create by wzq on 2020/11/4
 *
 */
class TestActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var containerView: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        containerView = binding.container
//        val dataStore =
//            AppInitializer.getInstance(this).initializeComponent(OtherInitializer::class.java)
//        val userId = preferencesKey<Int>("user_id")
//        lifecycleScope.launchWhenCreated {
//            dataStore.data.map {
//                it[userId]
//            }.collect {
//                Timber.i("user id = $it")
//            }
//        }


//        activity?.also {
//            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//                isEnabled = false; remove()
//                it.onBackPressed()
//            }
//        }
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.b1 -> {
                openPage(ImmersiveActivity::class)
            }
            R.id.b2 -> {
                showFragment(VideoPage())
            }
            R.id.b3 -> {
                showFragment(VideoWithViewPager2Page())
            }
            R.id.b4 -> {
                showFragment(ShadowPage())
            }
            R.id.b5 -> {
//                showFragment(AnimPage())
                openPage(AnimListActivity::class)
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        containerView.visibility = View.VISIBLE
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
        }
    }

    override fun onBackPressed() {
        if (containerView.isVisible) {
            containerView.removeAllViewsInLayout()
            containerView.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

}