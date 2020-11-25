package com.nick_sib.popularlibraries.mvp.model.repo.retrofit

import com.nick_sib.popularlibraries.mvp.model.api.IDataSource
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.GithubRepository
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import com.nick_sib.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import com.nick_sib.popularlibraries.mvp.model.network.INetworkStatus
import com.nick_sib.popularlibraries.mvp.model.repo.IGitUserRepos
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache и внедрить его сюда через интерфейс IRepositoriesCache
class RetrofitTheUserRepos(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
): IGitUserRepos {
    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserReposByLogin(user.login)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = db.userDao.findByLogin(user.login)
                            val roomRepos = repositories.map {
                                RoomGithubRepository(it.id, it.name, it.forksCount, roomUser?.id ?: 0L) }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login)
                    roomUser?.run{
                        db.repositoryDao.findForUser(id).map { GithubRepository(it.id, it.name, it.forksCount) }
                    } ?: throw RuntimeException("No such user in cache")
                }

            }
        }.subscribeOn(Schedulers.io())
}