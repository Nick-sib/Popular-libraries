package com.nick_sib.popularlibraries.mvp.model.entity


import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GithubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null
) : Parcelable
//{
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    ) {
//
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeInt(flags)
//    }
//
//    companion object CREATOR : Parcelable.Creator<GithubUser> {
//        override fun createFromParcel(parcel: Parcel): GithubUser {
//            return GithubUser(parcel)
//        }
//
//        override fun newArray(size: Int): Array<GithubUser?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//
//}