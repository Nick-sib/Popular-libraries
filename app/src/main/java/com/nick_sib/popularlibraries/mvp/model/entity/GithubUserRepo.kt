package com.nick_sib.popularlibraries.mvp.model.entity

import com.google.gson.annotations.Expose
import retrofit2.http.Url
import java.net.URL

data class GithubUserRepo (
    @Expose val id: Int = 0,
    @Expose val name: String? = null,
    @Url val forks_url: URL? = null
)