package com.nick_sib.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository (
    @Expose val id: Long = 0,
    @Expose val name: String = "",
    @Expose val forksCount: Int = 0
): Parcelable