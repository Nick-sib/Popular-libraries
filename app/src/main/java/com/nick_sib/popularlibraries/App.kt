package com.nick_sib.popularlibraries


import android.app.Application
import com.nick_sib.popularlibraries.di.AppComponent
import com.nick_sib.popularlibraries.di.DaggerAppComponent
import com.nick_sib.popularlibraries.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}