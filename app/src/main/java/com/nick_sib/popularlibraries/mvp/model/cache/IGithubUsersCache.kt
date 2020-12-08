package com.nick_sib.popularlibraries.mvp.model.cache

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single


interface IGithubUsersCache {
    fun setUsers(users: List<GithubUser>): Single<List<GithubUser>>
    fun getUsers(): Single<List<GithubUser>>
}