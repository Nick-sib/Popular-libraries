package com.nick_sib.popularlibraries.mvp.model.cache

import android.graphics.Bitmap
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUserAvatarCache {
    fun setAvatar(url: String, bitmap: Bitmap)//: Single<GithubUser>
    fun getAvatar(user: GithubUser): Single<Bitmap>
}