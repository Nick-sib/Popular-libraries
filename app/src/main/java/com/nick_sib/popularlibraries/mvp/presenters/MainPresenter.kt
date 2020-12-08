package com.nick_sib.popularlibraries.mvp.presenters


import com.nick_sib.popularlibraries.navigation.Screens
import com.nick_sib.popularlibraries.mvp.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}