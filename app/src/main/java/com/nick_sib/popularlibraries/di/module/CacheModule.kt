package com.nick_sib.popularlibraries.di.module

import androidx.room.Room
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.cache.IGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.cache.room.RoomGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()


    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)
}