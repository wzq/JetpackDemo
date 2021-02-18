package com.wzq.jetpack.test

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ActivityTestBinding
import com.wzq.jetpack.test.dialog.TestDialogActivity
import com.wzq.jetpack.test.shadow.ShadowPage
import com.wzq.jetpack.test.transition.TransActivity
import com.wzq.jetpack.util.ext.openPage

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
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.b1 -> {
                openPage(ImmersiveActivity::class)
            }
            R.id.b2 -> {
                openPage(TestNavActivity::class)
            }
            R.id.b3 -> {
            }
            R.id.b4 -> {
                showFragment(ShadowPage())
            }
            R.id.b5 -> {
//                showFragment(AnimPage())
                openPage(TransActivity::class)
            }
            R.id.b6 -> {
                openPage(TestDialogActivity::class)
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
//        activity?.also {
//            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//                isEnabled = false; remove()
//                it.onBackPressed()
//            }
//        }