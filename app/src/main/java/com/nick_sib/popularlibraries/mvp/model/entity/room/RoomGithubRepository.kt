package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RoomGithubRepository(
    @PrimaryKey var id: Long,
    var name: String,
    var forksCount: Int,
    var userId: Long
)