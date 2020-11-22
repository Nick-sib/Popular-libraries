package com.nick_sib.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TheUserView: MvpView {
    //просто чтобы отличалось от UsersView хотя разумнее делать индентично
    //и добавить  fun updateList()
    fun init()
    fun beginLoading()
    fun endLoading()
    fun showError(errorText: String)

}