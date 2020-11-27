package com.nick_sib.popularlibraries.mvp.view.image


import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nick_sib.popularlibraries.mvp.model.cache.IUserAvatarCache

class GlideImageLoader(private val cache: IUserAvatarCache) : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView, userLogin: String) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //Достаём из кеша
                    return false
                }

                override fun onResourceReady(
                    bitmap: Bitmap,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    //Кешируем
                    if (isFirstResource) cache.setAvatar(userLogin, bitmap)
                    return false
                }
            })
            .into(container)
    }
}
