package com.nick_sib.popularlibraries.mvp.model.repo

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGitUserRepos {
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
}