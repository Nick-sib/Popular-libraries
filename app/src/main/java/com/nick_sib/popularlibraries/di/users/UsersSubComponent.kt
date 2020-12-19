package com.nick_sib.popularlibraries.di.users

import com.nick_sib.popularlibraries.di.repository.RepositorySubComponent
import com.nick_sib.popularlibraries.di.users.module.UsersModule
import com.nick_sib.popularlibraries.mvp.presenters.UsersPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)

interface UsersSubComponent {
    fun repositorySubComponent(): RepositorySubComponent

    fun inject(usersPresenter: UsersPresenter)
}
