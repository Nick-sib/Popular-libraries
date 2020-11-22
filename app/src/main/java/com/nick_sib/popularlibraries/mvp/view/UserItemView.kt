package com.nick_sib.popularlibraries.mvp.view


interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}