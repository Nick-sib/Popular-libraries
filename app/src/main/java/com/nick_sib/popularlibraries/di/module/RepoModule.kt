package com.nick_sib.popularlibraries.di.module

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubReposCache
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGitUserRepos
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsers
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitTheUserRepos
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsers = RetrofitGithubUsers(api, networkStatus, cache)

}