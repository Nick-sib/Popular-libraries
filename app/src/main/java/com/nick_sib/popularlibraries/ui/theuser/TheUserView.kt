package com.nick_sib.popularlibraries.ui.theuser

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TheUserView: MvpView {
    fun init()
}