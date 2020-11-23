package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.nick_sib.popularlibraries.mvp.presenters.list.IForkListPresenter
import com.nick_sib.popularlibraries.mvp.view.ForkItemView
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

/**Презентер отдельного пользователя по которому кликнули*/
class TheUserPresenter (
    private val mainThreadScheduler: Scheduler,
    private val usersRepo: RetrofitGithubUsersRepo,
    private val theUserData: GithubUser
) : MvpPresenter<TheUserView>() {

    class ForkListPresenter : IForkListPresenter {
        val forks = mutableListOf<GithubUserRepo>()
        override var itemClickListener: ((ForkItemView) -> Unit)? = null
        override fun getCount() = forks.size

        override fun bindView(view: ForkItemView) {
            val fork = forks[view.pos]
            fork.name?.let {
                view.setForkName(it)
            }
        }
    }

    val forksListPresenter = ForkListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        //fork click
    }

    private fun loadData() {
        theUserData.login?.also {userLogin ->
            viewState.beginLoading()
            usersRepo.getUserRepos(userLogin)
                .observeOn(mainThreadScheduler)
                .subscribe({ repos ->
                    forksListPresenter.forks.clear()
                    forksListPresenter.forks.addAll(repos)
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

}