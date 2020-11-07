package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.navigation.Screens
import com.nick_sib.popularlibraries.mvp.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}