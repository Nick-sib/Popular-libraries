package com.nick_sib.popularlibraries.mvp.model.cache.room

import com.nick_sib.popularlibraries.mvp.model.cache.IGithubReposCache
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubReposCache (private val db: Database) : IGithubReposCache {
    override fun putRepos(repositories: List<GithubRepository>, user: GithubUser): Completable =
        Completable.fromAction{
            val roomUser = db.userDao.findByLogin(user.login)
            val roomRepos = repositories.map {
                RoomGithubRepository(it.id, it.name, it.forksCount, roomUser?.id ?: 0L) }
            db.repositoryDao.insert(roomRepos)
        }

    override fun getRepos(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
            roomUser?.run{
                db.repositoryDao.findForUser(id).map { GithubRepository(it.id, it.name, it.forksCount) }
            } ?: throw RuntimeException("No such user in cache")
        }


}