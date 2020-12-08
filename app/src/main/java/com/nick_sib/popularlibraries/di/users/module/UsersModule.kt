package com.nick_sib.popularlibraries.di.users.module


import com.nick_sib.popularlibraries.di.users.UsersScope
import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.cache.room.RoomGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsers
import dagger.Module
import dagger.Provides


@Module
class UsersModule {

    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @UsersScope
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsers = RetrofitGithubUsers(api, networkStatus, cache)
}