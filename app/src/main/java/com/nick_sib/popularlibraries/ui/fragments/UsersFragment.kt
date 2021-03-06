package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.databinding.FragmentUsersBinding
import com.nick_sib.popularlibraries.di.users.UsersSubComponent
import com.nick_sib.popularlibraries.mvp.view.image.GlideImageLoader
import com.nick_sib.popularlibraries.mvp.presenters.UsersPresenter
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), LoadedView {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var binding: FragmentUsersBinding? = null

    private var usersSubComponent: UsersSubComponent? = null

    private val presenter: UsersPresenter by moxyPresenter {
        usersSubComponent = App.instance.initUserSubComponent()
        UsersPresenter().apply {
            usersSubComponent?.inject(this)
        }
    }

    private val adapter: UsersRVAdapter by lazy {
        UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  = FragmentUsersBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun init() {
        binding?.apply {
            rvUsers.adapter = adapter
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun beginLoading(){
        binding?.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun endLoading(){
        binding?.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        binding?.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun release() {
        usersSubComponent = null
        App.instance.releaseUserSubComponent()
    }
}

