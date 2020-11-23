package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nick_sib.popularlibraries.ApiHolder
import com.nick_sib.popularlibraries.databinding.FragmentTheuserBinding
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.nick_sib.popularlibraries.mvp.presenters.TheUserPresenter
import com.nick_sib.popularlibraries.mvp.view.TheUserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TheUserFragment : MvpAppCompatFragment(), TheUserView {

    companion object {
        private const val USER_DATA = "user_data"

        fun newInstance(userData: GithubUser) = TheUserFragment().apply{
            arguments = Bundle().apply {
                putParcelable(USER_DATA, userData)
            }
        }
    }

    private var binding: FragmentTheuserBinding? = null


    private val presenter: TheUserPresenter by moxyPresenter {
        TheUserPresenter(RetrofitGithubUsersRepo(ApiHolder.api))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  = FragmentTheuserBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.theUserData = arguments?.getParcelable(USER_DATA)
            ?: GithubUser(null, null, null)
    }

    override fun init() {
        binding?.apply {
            bitvLogin.text =  presenter.theUserData.login
            bitvId.text =  presenter.theUserData.id
            Glide.with(this@TheUserFragment)
                .asBitmap()
                .load(presenter.theUserData.avatarUrl)
                .into(ivAvatar)
        }

    }
    
    override fun beginLoading() {
        //progressBar.visibility = View.VISIBLE
    }

    override fun endLoading() {

       // progressBar.visibility = View.GONE
    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }


}