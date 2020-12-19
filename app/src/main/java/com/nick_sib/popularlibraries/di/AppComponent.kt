package com.nick_sib.popularlibraries.di

import com.nick_sib.popularlibraries.di.module.*
import com.nick_sib.popularlibraries.di.users.UsersSubComponent
import com.nick_sib.popularlibraries.mvp.presenters.MainPresenter
import com.nick_sib.popularlibraries.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
    ]
)

interface AppComponent {
    fun usersSubComponent() : UsersSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}
