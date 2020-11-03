package com.nick_sib.popularlibraries

class MainPresenter (private val view: MainView, size: Int) {
    private val model = CountersModel.getInstance(size)

    fun counterClick(index: Int){
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }

}
