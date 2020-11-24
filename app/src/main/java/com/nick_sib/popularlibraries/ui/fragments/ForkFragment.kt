package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.nick_sib.popularlibraries.ApiHolder
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.databinding.FragmentForkBinding
import com.nick_sib.popularlibraries.databinding.FragmentTheuserBinding
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.nick_sib.popularlibraries.mvp.presenters.ForksPresenter
import com.nick_sib.popularlibraries.mvp.presenters.TheUserPresenter
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.ui.adapter.ForkUsersRVAdapter
import com.nick_sib.popularlibraries.ui.adapter.ForksRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.net.URL

class ForkFragment : MvpAppCompatFragment() , LoadedView  {

    companion object {
        private const val FORK_DATA = "fork_data"

        fun newInstance(forkData: String) = ForkFragment().apply{
            arguments = Bundle().apply {
                putString(FORK_DATA, forkData)
            }
        }
    }

    private var binding: FragmentForkBinding? = null

    private lateinit var forkData: String

    private val presenter: ForksPresenter by moxyPresenter {
        ForksPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            forkData)
    }


    private val adapter: ForkUsersRVAdapter by lazy {
        ForkUsersRVAdapter(presenter.forkUsersListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentForkBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        forkData = arguments?.getString(FORK_DATA)
            ?: ""
        super.onCreate(savedInstanceState)
    }


    override fun init() {
        binding?.apply {
            tvForkURL.text =  forkData
            rvForkData.adapter = adapter
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