package com.nick_sib.popularlibraries.mvp.model

import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsersRX(): Observable<GithubUser> {
        return Observable.create<GithubUser> { emitter ->
            try {
                val list = repositories
                list.forEach{ user ->
                    Thread.sleep(3000)//имитируем бурную деятельность
                    emitter.onNext(user)
                }
            } catch (e: Throwable){
                emitter.onError(e)
            }
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserRX(userIndex: Int): Observable<GithubUser> =
        Observable
            .just(repositories[userIndex])
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}