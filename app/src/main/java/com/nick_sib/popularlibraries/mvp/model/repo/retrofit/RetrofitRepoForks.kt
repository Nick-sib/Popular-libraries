package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.entity.ForkUser
import com.nick_sib.popularlibraries.mvp.model.repo.IGitRepoForks
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitRepoForks (private val api: IDataSource): IGitRepoForks {
    override fun getForksRepos(fork: String): Single<List<ForkUser>> =
        api.getForkUsers(fork).subscribeOn(Schedulers.io())
}