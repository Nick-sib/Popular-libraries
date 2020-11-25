package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGithubUsers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
): IGithubUsers {
    override fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user -> RoomGithubUser(user.id, user.login, user.avatarUrl,"") }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}
