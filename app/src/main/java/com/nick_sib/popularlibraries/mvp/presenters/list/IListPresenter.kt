package com.nick_sib.popularlibraries.mvp.presenters.list

import com.nick_sib.popularlibraries.mvp.view.IItemView
import com.nick_sib.popularlibraries.mvp.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

