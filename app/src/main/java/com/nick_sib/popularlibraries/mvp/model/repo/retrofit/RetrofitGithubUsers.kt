package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUsersCache
): IGithubUsers {
    override fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap {
                    cache.setUsers(it)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}
