package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.mvp.model.GithubUsersRepo
import com.nick_sib.popularlibraries.mvp.presenters.UsersPresenter
import com.nick_sib.popularlibraries.mvp.view.UsersView
import com.nick_sib.popularlibraries.ui.adapter.UsersRVAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment : MvpAppCompatFragment(), UsersView {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
    }

    private val adapter: UsersRVAdapter by lazy {
        UsersRVAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun beginLoading(){
        progressBar.visibility = View.VISIBLE
    }

    override fun endLoading(){
        progressBar.visibility = View.GONE
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        progressBar.visibility = View.GONE
    }
}
