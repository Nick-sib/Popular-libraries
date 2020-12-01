package com.nick_sib.popularlibraries.mvp.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}