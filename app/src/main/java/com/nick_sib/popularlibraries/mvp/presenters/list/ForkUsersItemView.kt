package com.nick_sib.popularlibraries.mvp.presenters.list

import com.nick_sib.popularlibraries.mvp.view.IItemView

interface ForkUsersItemView: IItemView {
    fun setForkUserName(text: String)
}