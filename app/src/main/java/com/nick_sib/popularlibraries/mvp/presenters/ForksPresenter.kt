package com.nick_sib.popularlibraries.mvp.presenters

import com.nick_sib.popularlibraries.mvp.model.entity.ForkUser
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsersRepo
import com.nick_sib.popularlibraries.mvp.presenters.list.ForkUsersItemView
import com.nick_sib.popularlibraries.mvp.presenters.list.IForkUsersListPresenter
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ForksPresenter(
    private val mainThreadScheduler: Scheduler,
    private val forksRepo: IGithubUsersRepo,
    private val forkUsersURL: String,
): MvpPresenter<LoadedView>() {
    class ForkUsersListPresenter : IForkUsersListPresenter {
        val users = mutableListOf<ForkUser>()
        override var itemClickListener: ((ForkUsersItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: ForkUsersItemView) {
            val user = users[view.pos]
            user.full_name?.let {
                view.setForkUserName(it)
            }

        }
    }

    val forkUsersListPresenter = ForkUsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

//        usersListPresenter.itemClickListener = { itemView ->
//            val user = usersListPresenter.users[itemView.pos]
//            router.navigateTo(Screens.TheUserScreen(user))
//        }
    }

    private fun loadData() {
        forksRepo.getForksRepos(forkUsersURL)
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                forkUsersListPresenter.users.clear()
                forkUsersListPresenter.users.addAll(repos)
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