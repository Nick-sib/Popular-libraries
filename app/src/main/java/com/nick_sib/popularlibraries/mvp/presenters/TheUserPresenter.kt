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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        viewState.beginLoading()
        userIndex?.run {
            usersRepo.getUserRX(this)
                . subscribe {
                    it?.let {
                        theUserData = it
                    } ?: viewState.showError("Empty user ID")
                }
        } ?: viewState.showError("Empty user ID")
        viewState.endLoading()
    }

}