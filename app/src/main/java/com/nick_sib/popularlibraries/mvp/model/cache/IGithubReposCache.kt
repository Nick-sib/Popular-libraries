package com.nick_sib.popularlibraries.mvp.model.cache

import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubReposCache {
    fun putRepos(repositories: List<GithubRepository>, user: GithubUser): Completable
    fun getRepos(user: GithubUser): Single<List<GithubRepository>>
}