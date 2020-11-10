package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.mvp.model.GithubUsersRepo
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import moxy.MvpPresenter
/**Презентер отдельного пользователя по которому кликнули*/
class TheUserPresenter (
    private val usersRepo: GithubUsersRepo,
) : MvpPresenter<TheUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun theUserData(userIndex: Int) = usersRepo.getUser(userIndex)

}