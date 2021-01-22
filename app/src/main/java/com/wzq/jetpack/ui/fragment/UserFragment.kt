package com.wzq.jetpack.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.FragmentUserBinding
import com.wzq.jetpack.ui.adapter.PageArticleAdapter
import com.wzq.jetpack.util.GlideApp
import com.wzq.jetpack.util.ext.dp
import com.wzq.jetpack.util.monitor.LoginMonitor
import com.wzq.jetpack.viewmodel.UserViewModel

class UserFragment : BaseFragment(R.layout.fragment_user) {

    private val loginMonitor = LoginMonitor()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentUserBinding.bind(view)
        loginMonitor.observe(viewLifecycleOwner) {
            binding.toolbar.title = it
        }

//        val defHeadIcon = "http://193.149.161.209/static/images/p001.png"
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