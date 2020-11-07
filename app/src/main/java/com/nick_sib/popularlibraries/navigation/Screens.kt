package com.nick_sib.popularlibraries.navigation

import com.nick_sib.popularlibraries.ui.theuser.TheUserFragment
import com.nick_sib.popularlibraries.ui.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class TheUserScreen(private var userIndex: Int) : SupportAppScreen() {

        override fun getFragment() = TheUserFragment.newInstance(userIndex)
    }
}