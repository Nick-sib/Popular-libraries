package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import com.nick_sib.popularlibraries.mvp.model.repo.IGitUserRepos
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitTheUserRepos(private val api: IDataSource): IGitUserRepos {
    override fun getUserRepos(login: String): Single<List<GithubUserRepo>> =
        api.getUserRepos(login).subscribeOn(Schedulers.io())
}