package com.nick_sib.popularlibraries.mvp.model.repo

import com.nick_sib.popularlibraries.mvp.model.entity.ForkUser
import io.reactivex.rxjava3.core.Single

interface IGitRepoForks {
    fun getForksRepos(fork: String): Single<List<ForkUser>>
}