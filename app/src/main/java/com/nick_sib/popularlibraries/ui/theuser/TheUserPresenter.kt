package com.nick_sib.popularlibraries.ui.theuser

import android.util.Log
import com.nick_sib.popularlibraries.repo.GithubUser
import com.nick_sib.popularlibraries.repo.GithubUsersRepo
import com.nick_sib.popularlibraries.ui.Screens
import com.nick_sib.popularlibraries.ui.users.UsersPresenter
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class TheUserPresenter (
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
) : MvpPresenter<TheUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun theUserData(userIndex: Int) = usersRepo.getUser(userIndex)


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}