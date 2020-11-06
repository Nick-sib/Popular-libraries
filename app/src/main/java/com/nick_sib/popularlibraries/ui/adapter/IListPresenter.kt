package com.nick_sib.popularlibraries.ui.adapter

import com.nick_sib.popularlibraries.ui.users.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>