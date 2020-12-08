package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey var id: Long,
    var login: String,
    var avatarUrl: String,
    //var reposUrl: String//Поле для локального кеша картинки
    var avatarPath: String//Поле для локального кеша картинки
)