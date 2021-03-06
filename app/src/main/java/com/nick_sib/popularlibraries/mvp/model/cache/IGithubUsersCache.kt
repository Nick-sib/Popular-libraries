package com.nick_sib.popularlibraries.mvp.model.cache

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface IGithubUsersCache {
    fun putUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}