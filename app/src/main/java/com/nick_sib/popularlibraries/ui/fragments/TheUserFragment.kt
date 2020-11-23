package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.nick_sib.popularlibraries.mvp.view.image.GlideImageLoader
import com.nick_sib.popularlibraries.ui.adapter.ForksRVAdapter
import com.nick_sib.popularlibraries.ui.adapter.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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

    lateinit var theUserData: GithubUser

    private val presenter: TheUserPresenter by moxyPresenter {
        TheUserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            theUserData)
    }


    private val adapter: ForksRVAdapter by lazy {
        ForksRVAdapter(presenter.forksListPresenter)
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
        theUserData = arguments?.getParcelable(USER_DATA)
            ?: GithubUser(null, null, null)
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        binding?.apply {
            bitvLogin.text =  theUserData.login
            tvId.text =  theUserData.id
            Glide.with(this@TheUserFragment)
                .asBitmap()
                .load(theUserData.avatarUrl)
                .into(ivAvatar)
            rvForks.adapter = adapter
        }
    }
    
    override fun beginLoading() {
        binding?.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
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