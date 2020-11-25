package com.nick_sib.popularlibraries.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.databinding.FragmentForkBinding
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository


class ForkFragment : Fragment() {

    companion object {
        private const val REPO_DATA = "fork_data"

        fun newInstance(repoData: GithubRepository) = ForkFragment().apply{
            arguments = Bundle().apply {
                putParcelable(REPO_DATA, repoData)
            }
        }
    }

    private var binding: FragmentForkBinding? = null

    private lateinit var repoData: GithubRepository


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
        repoData = arguments?.getParcelable(REPO_DATA)
            ?: GithubRepository(0, "empty", 0)
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            tvForkId.text = resources.getString(R.string.fork_id, repoData.id)
            tvForkTitle.text = resources.getString(R.string.fork_name, repoData.name)
            tvForkCount.text = resources.getString(R.string.fork_count, repoData.forksCount)
        }
    }

}