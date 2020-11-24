package com.nick_sib.popularlibraries.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nick_sib.popularlibraries.ApiHolder
import com.nick_sib.popularlibraries.databinding.FragmentForkBinding
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitGithubUsers
import com.nick_sib.popularlibraries.mvp.model.repo.retrofit.RetrofitRepoForks
import com.nick_sib.popularlibraries.mvp.presenters.ForksPresenter
import com.nick_sib.popularlibraries.mvp.view.LoadedView
import com.nick_sib.popularlibraries.ui.adapter.ForkUsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

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
            RetrofitRepoForks(ApiHolder.api),
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