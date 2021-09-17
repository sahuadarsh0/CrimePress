package com.techipinfotech.allindiacrimepress.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techipinfotech.allindiacrimepress.R

object BindingAdapters {

    @BindingAdapter("android:setImage")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl).apply(RequestOptions().circleCrop()).placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .into(view)
    }
}