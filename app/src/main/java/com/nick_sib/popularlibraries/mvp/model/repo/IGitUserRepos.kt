package com.nick_sib.popularlibraries.mvp.model.repo

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGitUserRepos {
    fun getUserRepos(login: String): Single<List<GithubUserRepo>>
}