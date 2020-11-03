package com.nick_sib.popularlibraries

class MainPresenter (private val view: MainView) {
    private val model = CountersModel()



    fun counterClick(index: Int){
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }

}
