package com.nick_sib.popularlibraries.mvp.presenters


import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitTheUserRepos
import com.nick_sib.popularlibraries.mvp.presenters.list.IForkListPresenter
import com.nick_sib.popularlibraries.mvp.view.ForkItemView
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

/**Презентер отдельного пользователя по которому кликнули*/
class TheUserPresenter (
    private val mainThreadScheduler: Scheduler,
    private val repos: RetrofitTheUserRepos,
    private val theUserData: GithubUser,
    private val router: Router = App.instance.router
) : MvpPresenter<LoadedView>() {

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

        forksListPresenter.itemClickListener = { itemView ->
            val fork = forksListPresenter.forks[itemView.pos]
            fork.forks_url?.run {
                router.navigateTo(Screens.ForksScreen(this))
            }
        }
    }

    private fun loadData() {
        theUserData.login?.also {userLogin ->
            viewState.beginLoading()
            repos.getUserRepos(userLogin)
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