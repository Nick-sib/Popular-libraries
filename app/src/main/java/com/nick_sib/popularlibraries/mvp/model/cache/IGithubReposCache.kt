package com.nick_sib.popularlibraries.mvp.model.cache

import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubReposCache {
    fun setRepos(repositories: List<GithubRepository>, user: GithubUser): Single<List<GithubRepository>>
    fun getRepos(user: GithubUser): Single<List<GithubRepository>>
}