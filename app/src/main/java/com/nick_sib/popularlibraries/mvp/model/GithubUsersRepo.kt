package com.nick_sib.popularlibraries.mvp.model

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser> = repositories

    fun getUser(userIndex: Int) : GithubUser = repositories[userIndex]
}