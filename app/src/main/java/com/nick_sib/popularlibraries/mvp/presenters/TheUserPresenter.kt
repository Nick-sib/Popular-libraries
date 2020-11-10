package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.mvp.model.GithubUsersRepo
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
/**Презентер отдельного пользователя по которому кликнули*/
class TheUserPresenter (
    private val usersRepo: GithubUsersRepo,
) : MvpPresenter<TheUserView>() {

    lateinit var theUserData: GithubUser
    var userIndex: Int? = null

    private val usersListObserver = object : Observer<GithubUser> {
        override fun onSubscribe(d: Disposable?) {
            viewState.beginLoading()
        }
        override fun onNext(users: GithubUser?) {
            users?.run {
                theUserData = this
                viewState.endLoading()
            } ?: run {
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userIndex?.run {
            usersRepo.getUserRX(this).subscribe(usersListObserver)
        } ?: viewState.showError("Empty user ID")

    }

}