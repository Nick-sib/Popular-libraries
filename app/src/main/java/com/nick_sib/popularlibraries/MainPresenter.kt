package com.nick_sib.popularlibraries

import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel): MvpPresenter<MainView>() {

    fun counterClick(buttonIndex: Int) {
        val nextValue = model.next(buttonIndex)
        viewState.setButtonText(buttonIndex, nextValue.toString())
    }

    fun fillButton(buttonIndex: Int) = model.getCurrent(buttonIndex).toString()
}
