package com.nick_sib.popularlibraries.di.module

import com.nick_sib.popularlibraries.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}