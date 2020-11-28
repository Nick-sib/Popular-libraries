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

//class App : Application() {
//    companion object {
//        lateinit var instance: App
//            private set
//    }
//
//    //Временно до даггера положим это тут
//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
//
//    val navigatorHolder: NavigatorHolder
//        get() = cicerone.navigatorHolder
//
//    val router: Router
//        get() = cicerone.router
//
//
//}