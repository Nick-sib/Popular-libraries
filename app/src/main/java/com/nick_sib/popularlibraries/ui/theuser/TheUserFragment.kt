package com.nick_sib.popularlibraries.ui.theuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.repo.GithubUsersRepo
import com.nick_sib.popularlibraries.ui.users.UsersPresenter
import kotlinx.android.synthetic.main.fragment_theuser.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TheUserFragment: MvpAppCompatFragment(), TheUserView {

    companion object {
        private const val USER_INDEX = "user_index"

        fun newInstance(userIndex: Int) = TheUserFragment().apply{
            arguments = Bundle().apply {
                putInt(USER_INDEX, userIndex)
            }
        }
    }

    private var userIndex: Int = 0

    private val presenter: TheUserPresenter by moxyPresenter {
        TheUserPresenter(GithubUsersRepo(), App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userIndex = arguments?.getInt(USER_INDEX) ?: 0
    }

    override fun init() {
       tv_login.text =  presenter.theUserData(userIndex).login
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =  View.inflate(context, R.layout.fragment_theuser, null)

}