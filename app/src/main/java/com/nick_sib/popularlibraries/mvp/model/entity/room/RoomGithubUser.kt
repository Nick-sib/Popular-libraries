package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey var id: Long,
    var login: String,
    var avatarUrl: String,
    var avatarPath: String//Поле для локального кеша картинки
)