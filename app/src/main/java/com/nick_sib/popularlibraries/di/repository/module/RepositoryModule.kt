package com.nick_sib.popularlibraries.di.repository.module

import com.nick_sib.popularlibraries.di.repository.RepositoryScope
import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubReposCache
import com.nick_sib.popularlibraries.mvp.model.cache.room.RoomGithubReposCache
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGitUserRepos
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitTheUserRepos
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun userRepositoriesCache(
        database: Database
    ): IGithubReposCache = RoomGithubReposCache(database)

    @RepositoryScope
    @Provides
    fun userRepositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubReposCache
    ): IGitUserRepos = RetrofitTheUserRepos(api, networkStatus, cache)
}