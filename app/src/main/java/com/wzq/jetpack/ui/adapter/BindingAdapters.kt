package com.wzq.jetpack.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wzq.jetpack.util.timeFormat


/**
 * Created by wzq on 2019-07-15
 *
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
//        view.movementMethod = LinkMovementMethod.getInstance()  该设置会拦截view的touch事件
    } else {
        view.text = ""
    }
}

@BindingAdapter("formatTime")
fun bindTimeFormat(view: TextView, time: Long?) {
    if (time != null){
        view.text = timeFormat(time)
    }else {
        view.text = ""
    }
}

@BindingAdapter("formatTime")
fun bindTimeFormat(view: TextView, time: String?) {
    view.text = if (time.isNullOrBlank()){
        ""
    }else {
        time.substring(0, time.indexOf("T"))
    }
}