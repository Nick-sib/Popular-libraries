package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nick_sib.popularlibraries.databinding.FragmentReposBinding
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.presenters.ReposPresenter
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.ui.adapter.ForksRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), LoadedView {

    companion object {
        private const val USER_DATA = "user_data"

        fun newInstance(user: GithubUser) = ReposFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_DATA, user)
            }
        }
    }

    private var binding: FragmentReposBinding? = null

    private lateinit var userData: GithubUser

    private val adapter: ForksRVAdapter by lazy {
        ForksRVAdapter(presenter.repoListPresenter)
    }

    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(userData)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentReposBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        userData = arguments?.getParcelable(USER_DATA)
            ?: let {
                showError("Извините пользователь не найден")
                GithubUser(0, "", "")
            }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    override fun init() {
        binding?.apply {
            bitvLogin.text =  userData.login
            tvId.text =  userData.id.toString()
            Glide.with(this@ReposFragment)
                .asBitmap()
                .load(userData.avatarUrl)
                .into(ivAvatar)
            rvForks.adapter = adapter
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun beginLoading() {
        binding?.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun endLoading() {
        binding?.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}