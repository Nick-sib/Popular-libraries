package com.nick_sib.popularlibraries.mvp.model.entity


import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubUser(
    @Expose val id: Long = 0,
    @Expose val login: String = "",
    @Expose val avatarUrl: String = ""
) : Parcelable
