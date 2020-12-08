package com.nick_sib.popularlibraries.mvp.view.list


interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String, userLogin: String)
}