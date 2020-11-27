package com.nick_sib.popularlibraries.mvp.model.cache.room

import com.nick_sib.popularlibraries.mvp.model.cache.IGithubUsersCache
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val db: Database) : IGithubUsersCache {
    override fun setUsers(users: List<GithubUser>): Single<List<GithubUser>> =
        Single.fromCallable {
            val roomUsers =
                users.map { user -> RoomGithubUser(user.id, user.login, user.avatarUrl, "") }
            db.userDao.insert(roomUsers)
            users
        }

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl)
        }
    }

}