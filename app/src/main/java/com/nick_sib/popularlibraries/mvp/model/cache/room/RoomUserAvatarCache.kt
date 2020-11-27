package com.nick_sib.popularlibraries.mvp.model.cache.room

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.cache.IUserAvatarCache
import com.nick_sib.popularlibraries.mvp.model.entity.GithubUser
import com.nick_sib.popularlibraries.mvp.model.entity.room.Database
import io.reactivex.rxjava3.core.Single
import java.io.File

class RoomUserAvatarCache(private val db: Database) : IUserAvatarCache {

    override fun setAvatar(userLogin: String, bitmap: Bitmap) {//}: Single<GithubUser> {

        if (!rootPath.exists()) rootPath.mkdirs()
        val result = String.format("%s/%s.png", rootPath, userLogin)
        //bitmap.
        //val user = db.userDao.findByLoginUrl(url)

        //Log.d("myLOG", "rootPath = $rootPath \n  userLogin = $userLogin")
//        Single.fromCallable {
//            val roomUsers =
//                user.map { user -> RoomGithubUser(user.id, user.login, user.avatarUrl, "") }
//            //db.userDao.insert(roomUsers)
//            user
//        }
    }

    override fun getAvatar(user: GithubUser): Single<Bitmap> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val IMAGE_FOLDER = "/dbCache"
        private val rootPath = File(App.instance.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + IMAGE_FOLDER)
    }
}