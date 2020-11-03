package com.nick_sib.popularlibraries

/**
 * @param size - count of working buttons
 * @param view - interfaced activity*/
class MainPresenter (private val view: MainView, size: Int) {
    private val model = CountersModel.getInstance(size)

    fun counterClick(index: Int){
        view.setButtonText(index, model.next(index).toString())
    }

    fun fillButton(index: Int) = model.getCurrent(index).toString()

}
