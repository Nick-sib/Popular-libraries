package com.nick_sib.popularlibraries.mvp.presenters


import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.presenters.list.IUserListPresenter
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import com.nick_sib.popularlibraries.mvp.view.UserItemView
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,
    private val users: IGithubUsers,
    private val router: Router = App.instance.router
) : MvpPresenter<LoadedView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.TheUserScreen(user))
        }
    }

    private fun loadData() {
        users.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

}