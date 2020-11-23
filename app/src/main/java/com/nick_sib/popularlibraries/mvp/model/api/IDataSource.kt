package com.nick_sib.popularlibraries.mvp.model.api


import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>


    @GET("users/{login}/repos")
    fun getUserRepos(@Path("login") login: String ): Single<List<GithubUserRepo>>
}