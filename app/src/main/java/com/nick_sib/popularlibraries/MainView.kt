package com.nick_sib.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

//@AddToEndSingle - существует еще вот такой алиас
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setButtonOneText(text: String)
    fun setButtonTwoText(text: String)
    fun setButtonThreeText(text: String)
}