package com.nick_sib.popularlibraries.ui

import com.nick_sib.popularlibraries.ui.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
}