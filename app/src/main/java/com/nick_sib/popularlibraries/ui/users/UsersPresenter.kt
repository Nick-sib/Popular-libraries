package com.nick_sib.popularlibraries.ui.users

import android.util.Log
import com.nick_sib.popularlibraries.repo.GithubUser
import com.nick_sib.popularlibraries.repo.GithubUsersRepo
import com.nick_sib.popularlibraries.ui.Screens
import com.nick_sib.popularlibraries.ui.adapter.IUserListPresenter
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            Log.d("myLOG", "onFirstViewAttach: ${it.pos}")
            router.navigateTo(Screens.TheUserScreen(it.pos))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}