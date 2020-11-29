package com.nick_sib.popularlibraries.di

import com.nick_sib.popularlibraries.di.module.*
import com.nick_sib.popularlibraries.mvp.presenters.MainPresenter
import com.nick_sib.popularlibraries.mvp.presenters.ReposPresenter
import com.nick_sib.popularlibraries.mvp.presenters.UsersPresenter
import com.nick_sib.popularlibraries.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(reposPresenter: ReposPresenter)
}
