package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import moxy.MvpPresenter

/**Презентер отдельного пользователя по которому кликнули*/
class TheUserPresenter (private val usersRepo: RetrofitGithubUsersRepo,
) : MvpPresenter<TheUserView>() {

    lateinit var theUserData: GithubUser

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
//        viewState.beginLoading()
//        userIndex?.run {
//            usersRepo.getUserRX(this)
//                .subscribe(
//                    {data -> theUserData = data},
//                    {err -> viewState.showError(err.toString())},
//                    {viewState.endLoading()}
//                )
//        } ?: viewState.showError("Empty user ID")
//
    }

}