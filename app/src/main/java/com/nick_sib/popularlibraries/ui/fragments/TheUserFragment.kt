package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.mvp.presenters.TheUserPresenter
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import kotlinx.android.synthetic.main.fragment_theuser.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TheUserFragment : MvpAppCompatFragment(), TheUserView {

    companion object {
        private const val USER_INDEX = "user_index"

        fun newInstance(userIndex: Int) = TheUserFragment().apply{
            arguments = Bundle().apply {
                putInt(USER_INDEX, userIndex)
            }
        }
    }


//    private val presenter: TheUserPresenter by moxyPresenter {
//        TheUserPresenter(GithubUsersRepo())
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =  View.inflate(context, R.layout.fragment_theuser, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //presenter.userIndex = arguments?.getInt(USER_INDEX) ?: 0
    }
    
    override fun beginLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun endLoading() {
       //tv_login.text =  presenter.theUserData.login
        progressBar.visibility = View.GONE
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }


}