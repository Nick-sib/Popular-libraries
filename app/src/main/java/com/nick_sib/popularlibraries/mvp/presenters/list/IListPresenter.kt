package com.nick_sib.popularlibraries.mvp.presenters.list

import com.nick_sib.popularlibraries.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

