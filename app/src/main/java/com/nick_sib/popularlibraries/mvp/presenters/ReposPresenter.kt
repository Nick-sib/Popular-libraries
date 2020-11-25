package com.nick_sib.popularlibraries.mvp.presenters


import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitTheUserRepos
import com.nick_sib.popularlibraries.mvp.presenters.list.IRepoListPresenter
import com.nick_sib.popularlibraries.mvp.view.list.RepoItemView
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

/**Презентер отдельного пользователя по которому кликнули*/
class ReposPresenter (
    private val scheduler: Scheduler,
    private val repos: RetrofitTheUserRepos,
    private val userData: GithubUser,
    private val router: Router = App.instance.router
) : MvpPresenter<LoadedView>() {

    inner class RepoListPresenter : IRepoListPresenter {
        var repos = listOf<GithubRepository>()
        internal set

        override var itemClickListener: ((RepoItemView) -> Unit)? = { itemView ->
            val repo = repos[itemView.pos]
            router.navigateTo(Screens.ForksScreen(repo))
        }

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
        }
    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        viewState.beginLoading()
        repos.getUserRepos(userData)
            .observeOn(scheduler)
            .subscribe({ repos ->
                repoListPresenter.repos = repos
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