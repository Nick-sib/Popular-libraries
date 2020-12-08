package com.nick_sib.popularlibraries


import android.app.Application
import com.nick_sib.popularlibraries.di.AppComponent
import com.nick_sib.popularlibraries.di.DaggerAppComponent
import com.nick_sib.popularlibraries.di.module.AppModule
import com.nick_sib.popularlibraries.di.repository.RepositorySubComponent
import com.nick_sib.popularlibraries.di.users.UsersSubComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    private var usersSubComponent: UsersSubComponent? = null

    private var repositorySubComponent: RepositorySubComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubComponent() = appComponent.usersSubComponent().also {
        usersSubComponent = it
    }

    fun releaseUserSubComponent() {
        usersSubComponent = null
    }

    fun initRepositorySubComponent() = usersSubComponent?.repositorySubComponent().also {
        repositorySubComponent = it
    }

    fun releaseRepositorySubComponent() {
        repositorySubComponent = null
    }
}