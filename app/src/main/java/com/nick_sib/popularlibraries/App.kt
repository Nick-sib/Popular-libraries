package com.nick_sib.popularlibraries

import android.app.Application
import android.os.Environment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import java.io.File

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }


    val imagesRoot: File?
        get() =  baseContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)


    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val navigatorHolder: NavigatorHolder
        get() = cicerone.navigatorHolder

    val router: Router
        get() = cicerone.router

}