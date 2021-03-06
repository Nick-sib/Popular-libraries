package com.nick_sib.popularlibraries.mvp.view.image


import android.widget.ImageView
import com.bumptech.glide.Glide


class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView, userLogin: String) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .into(container)
    }
}
