package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey var id: Long,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String//В моей версии Api это поле не используется
)