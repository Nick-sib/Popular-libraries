package com.nick_sib.popularlibraries.deleteme

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface Activity4View: MvpView {
    fun beginConvert(imagePath: Uri)
    fun endConvert(imagePath: Uri)
    fun progressConvert(progress: Int)
    fun showError(errorText: String)

}