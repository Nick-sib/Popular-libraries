package com.nick_sib.popularlibraries.mvp.presenters

import android.util.Log
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.GithubUsersRepo
import com.nick_sib.popularlibraries.navigation.Screens
import com.nick_sib.popularlibraries.mvp.presenters.list.IUserListPresenter
import com.nick_sib.popularlibraries.mvp.view.UserItemView
import com.nick_sib.popularlibraries.mvp.view.UsersView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
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

    private val usersListObserver = object : Observer<GithubUser> {
        override fun onSubscribe(d: Disposable?) {
            //можно сделать диалог с отменой загузки
            viewState.beginLoading()
        }
        override fun onNext(users: GithubUser?) {
            users?.run {
                usersListPresenter.users.add(this)
                viewState.updateList()
            } ?: run {
                Log.d("myLOG", "onNext: ")
                viewState.showError("Empty data")
            }
        }
        override fun onError(e: Throwable?) {
            viewState.showError(e.toString())
        }
        override fun onComplete() {
            viewState.endLoading()
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
        usersRepo.getUsersRX().subscribe(usersListObserver)
    }


}