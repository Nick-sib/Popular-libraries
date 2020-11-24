package com.nick_sib.popularlibraries.mvp.model.repo

import com.nick_sib.popularlibraries.mvp.model.entity.ForkUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepos(login: String): Single<List<GithubUserRepo>>
    fun getForksRepos(fork: String): Single<List<ForkUser>>
}