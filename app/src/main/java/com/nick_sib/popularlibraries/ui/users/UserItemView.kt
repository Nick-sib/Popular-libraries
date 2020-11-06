package com.nick_sib.popularlibraries.ui.users

import com.nick_sib.popularlibraries.ui.adapter.IItemView


interface UserItemView: IItemView {
    fun setLogin(text: String)
}