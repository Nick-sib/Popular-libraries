package com.nick_sib.popularlibraries.mvp.presenters


import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.presenters.list.IUserListPresenter
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import com.nick_sib.popularlibraries.mvp.view.list.UserItemView
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val scheduler: Scheduler,
    private val users: IGithubUsers,
    private val router: Router = App.instance.router
) : MvpPresenter<LoadedView>() {

    inner class UsersListPresenter : IUserListPresenter {
        var users = listOf<GithubUser>()
        internal set

        override var itemClickListener: ((UserItemView) -> Unit)? = { itemView ->
            val user = users[itemView.pos]
            router.navigateTo(Screens.TheUserScreen(user))
        }
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.loadAvatar(user.avatarUrl, user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        users.getUsers()
            .observeOn(scheduler)
            .subscribe({ repos ->
                usersListPresenter.users = repos
                viewState.updateList()
                viewState.endLoading()
            }, {
                it?.message?.run {
                    viewState.showError(this)
                }
                viewState.endLoading()
                println("Error: ${it.message}")
            })
    }

}