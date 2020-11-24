package com.nick_sib.popularlibraries.navigation

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.ui.fragments.ForkFragment
import com.nick_sib.popularlibraries.ui.fragments.UsersFragment
import com.nick_sib.popularlibraries.ui.fragments.TheUserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class TheUserScreen(private var userData: GithubUser) : SupportAppScreen() {
        override fun getFragment() = TheUserFragment.newInstance(userData)
    }

    class ForksScreen(private var forkURL: String) : SupportAppScreen() {
        override fun getFragment() = ForkFragment.newInstance(forkURL)
    }

}