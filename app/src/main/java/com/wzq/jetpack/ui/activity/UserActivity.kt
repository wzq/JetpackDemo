package com.wzq.jetpack.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentUserBinding
import com.wzq.jetpack.util.GlideApp
import com.wzq.jetpack.util.ext.dp
import com.wzq.jetpack.util.ext.openPage
import com.wzq.jetpack.util.monitor.LoginMonitor

class UserActivity : BaseActivity() {
    private val loginMonitor = LoginMonitor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginMonitor.observe(this) {
            binding.toolbar.title = it
        }

        binding.toolbar.setOnClickListener {
            if (!loginMonitor.isLogin()) {
                openPage(LoginActivity::class)
            }
        }

        val w = 32.dp.toInt()
        val target = object : CustomTarget<Drawable>(w, w) {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                binding.toolbar.navigationIcon = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                binding.toolbar.navigationIcon = placeholder
            }

        }
        GlideApp.with(this)
            .asDrawable()
            .load(R.mipmap.ic_launcher)
            .transform(CircleCrop())
            .into(target)

    }

}