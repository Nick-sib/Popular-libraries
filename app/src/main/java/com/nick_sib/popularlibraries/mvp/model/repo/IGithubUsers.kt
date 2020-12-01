package com.nick_sib.popularlibraries.mvp.model.repo

import com.nick_sib.popularlibraries.mvp.model.entity.ForkUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGithubUsers {
    fun getUsers(): Single<List<GithubUser>>

}