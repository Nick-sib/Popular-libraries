package com.nick_sib.popularlibraries.mvp.model.entity


import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null
) : Parcelable
