package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubReposCache
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGitUserRepos
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitTheUserRepos(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubReposCache
): IGitUserRepos {
    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserReposByLogin(user.login)
                    .flatMap {
                        cache.putRepos(it, user).toSingleDefault(it)
                    }
            } else {
                cache.getRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}