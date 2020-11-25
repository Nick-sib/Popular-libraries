package com.nick_sib.popularlibraries.mvp.model.api


import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>


    @GET("users/{login}/repos")
    fun getUserReposByLogin(@Path("login") login: String): Single<List<GithubRepository>>

    @GET
    fun getUserReposByUrl(@Url url: String): Single<List<GithubRepository>>
}