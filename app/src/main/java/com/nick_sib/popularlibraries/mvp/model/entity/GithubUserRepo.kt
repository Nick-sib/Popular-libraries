package com.nick_sib.popularlibraries.mvp.model.entity

import com.google.gson.annotations.Expose

data class GithubUserRepo (
    @Expose val id: Int = 0,
    @Expose val name: String? = null,
    @Expose val forks_url: String? = null
)