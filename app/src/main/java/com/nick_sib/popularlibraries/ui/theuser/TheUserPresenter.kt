package com.nick_sib.popularlibraries.ui.theuser

import com.nick_sib.popularlibraries.model.GithubUsersRepo
import moxy.MvpPresenter

class TheUserPresenter (
    private val usersRepo: GithubUsersRepo,
) : MvpPresenter<TheUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun theUserData(userIndex: Int) = usersRepo.getUser(userIndex)


}