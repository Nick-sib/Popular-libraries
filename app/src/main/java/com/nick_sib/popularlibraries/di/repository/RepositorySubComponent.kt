package com.nick_sib.popularlibraries.di.repository

import com.nick_sib.popularlibraries.di.repository.module.RepositoryModule
import com.nick_sib.popularlibraries.mvp.presenters.ReposPresenter
import dagger.Subcomponent


@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)

interface RepositorySubComponent {
    fun inject(reposPresenter: ReposPresenter)
}